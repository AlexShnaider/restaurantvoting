package ru.restaurantvoting.repository;

import ru.restaurantvoting.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    Vote save(Vote vote);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Vote get(int id, int userId);

    // null if meal do not belong to userId
    Vote getWithUserAndRestaurant(int id, int userId);

    //sorted by dateTime
    List<Vote> getAll();

    //sorted by dateTime
    List<Vote> getAllBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    //sorted by dateTime
    List<Vote> getByUserBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int userId);

    //sorted by dateTime
    List<Vote> getByRestaurantBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int restaurantId);
}