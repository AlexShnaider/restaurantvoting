package ru.restaurantvoting.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurantvoting.model.Vote;
import ru.restaurantvoting.repository.VoteRepository;
import ru.restaurantvoting.util.exception.IllegalRequestDataException;
import ru.restaurantvoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository repository;

    @Override
    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Vote get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public Vote update(Vote vote) throws NotFoundException, IllegalRequestDataException {
        if (LocalTime.now().compareTo(LocalTime.of(11, 0)) > 0) {
            throw new IllegalRequestDataException("Vote couldn't be updated after 11:00");
        }
        return checkNotFoundWithId(repository.save(vote), vote.getId());
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Vote> getAllBetweenDates(LocalDate startDate, LocalDate endDate) {
        return repository.getAllBetweenDates(startDate, endDate);
    }

    @Override
    public List<Vote> getByUser(int userId) {
        return repository.getByUser(userId);
    }

    @Override
    public List<Vote> getByRestaurant(int restaurantId) {
        return repository.getByRestaurant(restaurantId);
    }

    @Override
    public List<Vote> getByUserBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.getByUserBetweenDates(startDate, endDate, userId);
    }

    @Override
    public List<Vote> getByRestaurantBetweenDates(LocalDate startDate, LocalDate endDate, int restaurantId) {
        return repository.getByRestaurantBetweenDates(startDate, endDate, restaurantId);
    }
}
