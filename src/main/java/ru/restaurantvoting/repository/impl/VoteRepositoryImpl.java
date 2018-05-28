package ru.restaurantvoting.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.restaurantvoting.model.User;
import ru.restaurantvoting.model.Vote;
import ru.restaurantvoting.repository.UserRepository;
import ru.restaurantvoting.repository.VoteRepository;
import ru.restaurantvoting.repository.crud.CrudUserRepository;
import ru.restaurantvoting.repository.crud.CrudVoteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {
    private static final Sort SORT_DATE_TIME = new Sort(Sort.Direction.ASC, "dateTime");

    @Autowired
    private CrudVoteRepository crudRepository;

    @Override
    public Vote save(Vote vote) {
        return crudRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return crudRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null);
    }

    @Override
    public Vote getWithUserAndRestaurant(int id, int userId) {
        return crudRepository.getWithUserAndRestaurant(id, userId);
    }

    @Override
    public List<Vote> getAll() {
        return crudRepository.findAll(SORT_DATE_TIME);
    }

    @Override
    public List<Vote> getAllBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return crudRepository.getAllBetweenDates(startDate, endDate);
    }

    @Override
    public List<Vote> getByUserBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.getByUserBetweenDates(startDate, endDate, userId);
    }

    @Override
    public List<Vote> getByRestaurantBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int restaurantId) {
        return crudRepository.getByRestaurantBetweenDates(startDate, endDate, restaurantId);
    }
}
