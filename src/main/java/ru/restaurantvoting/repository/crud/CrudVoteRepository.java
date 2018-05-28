package ru.restaurantvoting.repository.crud;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurantvoting.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v")
    List<Vote> getAll();

    @Override
    List<Vote> findAll(Sort sort);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.dateTime BETWEEN :startDate AND :endDate ORDER BY v.dateTime")
    List<Vote> getAllBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.user.id=:userId " +
            "AND v.dateTime BETWEEN :startDate AND :endDate ORDER BY v.dateTime")
    List<Vote> getByUserBetweenDates(
            @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate,
            @Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.restaurant.id=:restaurantId " +
            "AND v.dateTime BETWEEN :startDate AND :endDate ORDER BY v.dateTime")
    List<Vote> getByRestaurantBetweenDates(
            @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate,
            @Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user JOIN FETCH v.restaurant WHERE v.id = ?1 AND v.user.id = ?2")
    Vote getWithUserAndRestaurant(int id, int userId);
/*
//    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"meals"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithMeals(int id);*/
}
