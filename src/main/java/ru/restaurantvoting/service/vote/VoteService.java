package ru.restaurantvoting.service.vote;

import ru.restaurantvoting.model.Vote;
import ru.restaurantvoting.util.exception.IllegalRequestDataException;
import ru.restaurantvoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote create(Vote vote);

    void delete(int id, int userId) throws NotFoundException;

    Vote get(int id, int userId) throws NotFoundException;

    Vote update(Vote vote) throws NotFoundException, IllegalRequestDataException;

    List<Vote> getAll();

    List<Vote> getAllBetweenDates(LocalDate startDate, LocalDate endDate);

    List<Vote> getByUser(int userId);

    List<Vote> getByRestaurant(int restaurantId);

    List<Vote> getByUserBetweenDates(LocalDate startDate, LocalDate endDate, int userId);

    List<Vote> getByRestaurantBetweenDates(LocalDate startDate, LocalDate endDate, int restaurantId);

}