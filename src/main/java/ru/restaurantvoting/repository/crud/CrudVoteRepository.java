package ru.restaurantvoting.repository.crud;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurantvoting.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.userId=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Override
    List<Vote> findAll(Sort sort);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.date BETWEEN :startDate AND :endDate ORDER BY v.date")
    List<Vote> getAllBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.userId=:userId ORDER BY v.date")
    List<Vote> getByUser(@Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.restaurantId=:restaurantId ORDER BY v.date")
    List<Vote> getByRestaurant(@Param("restaurantId") int restaurantId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.userId=:userId " +
            "AND v.date BETWEEN :startDate AND :endDate ORDER BY v.date")
    List<Vote> getByUserBetweenDates(
            @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
            @Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.restaurantId=:restaurantId " +
            "AND v.date BETWEEN :startDate AND :endDate ORDER BY v.date")
    List<Vote> getByRestaurantBetweenDates(
            @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
            @Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user JOIN FETCH v.restaurant WHERE v.id = ?1 AND v.user.id = ?2")
    Vote getWithUserAndRestaurant(int id, int userId);
}
