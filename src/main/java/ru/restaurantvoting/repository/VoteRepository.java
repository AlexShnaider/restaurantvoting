package ru.restaurantvoting.repository;

import ru.restaurantvoting.model.Vote;

import java.time.LocalDate;
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
    List<Vote> getAllBetweenDates(LocalDate startDate, LocalDate endDate);

    //sorted by dateTime
    List<Vote> getByUser(int userId);

    //sorted by dateTime
    List<Vote> getByRestaurant(int restaurantId);

    //sorted by dateTime
    List<Vote> getByUserBetweenDates(LocalDate startDate, LocalDate endDate, int userId);

    //sorted by dateTime
    List<Vote> getByRestaurantBetweenDates(LocalDate startDate, LocalDate endDate, int restaurantId);
}