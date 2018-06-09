package ru.restaurantvoting.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurantvoting.model.Vote;
import ru.restaurantvoting.service.vote.VoteService;
import ru.restaurantvoting.to.VoteTo;
import ru.restaurantvoting.util.DateUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.restaurantvoting.util.DateUtil.MAX_DATE;
import static ru.restaurantvoting.util.DateUtil.MIN_DATE;
import static ru.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.restaurantvoting.util.ValidationUtil.checkNew;
import static ru.restaurantvoting.util.VoteUtil.*;

public abstract class AbstractVoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public VoteTo get(int id, int userId) {
        log.info("get vote {}", id);
        return asTo(service.get(id, userId));
    }

    public void delete(int id, int userId) {
        log.info("delete vote {}", id);
        service.delete(id,userId);
    }

    public List<VoteTo> getAll() {
        log.info("getAll");
        return asTo(service.getAll());
    }

    public List<VoteTo> getAllBetweenDates(LocalDate startDate, LocalDate endDate) {
        startDate = DateUtil.orElse(startDate, MIN_DATE);
        endDate = DateUtil.orElse(endDate, MAX_DATE);
        log.info("getAllBetweenDates {} and {}", startDate, endDate);
        return asTo(service.getAllBetweenDates(startDate, endDate));
    }

    public List<VoteTo> getByUser(int userId) {
        log.info("getAll by user {}", userId);
        return asTo(service.getByUser(userId));
    }

    public List<VoteTo> getByRestaurant(int restaurantId) {
        log.info("getAll by restaurant {}", restaurantId);
        return asTo(service.getByRestaurant(restaurantId));
    }

    public List<VoteTo> getByUserBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        startDate = DateUtil.orElse(startDate, MIN_DATE);
        endDate = DateUtil.orElse(endDate, MAX_DATE);
        log.info("getAllBetweenDates {} and {} by user {}", startDate, endDate, userId);
        return asTo(service.getByUserBetweenDates(startDate, endDate, userId));
    }

    public List<VoteTo> getByRestaurantBetweenDates(LocalDate startDate, LocalDate endDate, int restaurantId) {
        startDate = DateUtil.orElse(startDate, MIN_DATE);
        endDate = DateUtil.orElse(endDate, MAX_DATE);
        log.info("getAllBetweenDates {} and {} by restaurant {}", startDate, endDate, restaurantId);
        return asTo(service.getByRestaurantBetweenDates(startDate, endDate, restaurantId));
    }

    @Transactional
    public VoteTo update(VoteTo voteTo, int id, int userId) {
        assureIdConsistent(voteTo, id);
        log.info("update vote {}", voteTo);
        Vote vote = updateFromTo(new Vote(service.get(id, userId)), voteTo);
        return asTo(service.update(vote));
    }

    public VoteTo create(VoteTo voteTo) {
        log.info("create vote {}", voteTo);
        checkNew(voteTo);
        return asTo(service.create(createNewFromTo(voteTo)));
    }
}