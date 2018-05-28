package ru.restaurantvoting.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import ru.restaurantvoting.RestaurantTestData;
import ru.restaurantvoting.UserTestData;
import ru.restaurantvoting.model.Vote;
import java.util.Collections;

import static ru.restaurantvoting.RestaurantTestData.*;
import static ru.restaurantvoting.UserTestData.USER1;
import static ru.restaurantvoting.UserTestData.USER1_ID;
import static ru.restaurantvoting.VoteTestData.*;

public class VoteRepositoryImplTest extends BaseRepositoryImplTest{

    @Test
    public void save() {
        assertMatch(voteRepository.getAll(), VOTE1, VOTE2, VOTE3);
        Vote vote = voteRepository.save(NEW_VOTE);
        assertMatch(vote, NEW_VOTE);
        assertMatch(voteRepository.getAll(), VOTE1, VOTE2, VOTE3, NEW_VOTE);
    }

    @Test
    public void delete() {
        assertMatch(voteRepository.getAll(), VOTE1, VOTE2, VOTE3);
        Assert.assertTrue(voteRepository.delete(VOTE1_ID, USER1_ID));
        assertMatch(voteRepository.getAll(), VOTE2, VOTE3);
    }

    @Test
    public void deleteNotExisted() {
        assertMatch(voteRepository.getAll(), VOTE1, VOTE2, VOTE3);
        Assert.assertFalse(voteRepository.delete(VOTE1_ID, 10));
        Assert.assertFalse(voteRepository.delete(10, USER1_ID));
        assertMatch(voteRepository.getAll(), VOTE1, VOTE2, VOTE3);
    }

    @Test
    public void get() {
        Vote vote = voteRepository.getWithUserAndRestaurant(VOTE1_ID, USER1_ID);
        assertMatch(vote, VOTE1);
    }

    @Test
    public void getNotExisted() {
        Assert.assertNull(voteRepository.getWithUserAndRestaurant(10, USER1_ID));
        Assert.assertNull(voteRepository.getWithUserAndRestaurant(VOTE1_ID, 10));
    }

    @Test
    public void getWithUserAndRestaurant() {
        Vote vote = voteRepository.getWithUserAndRestaurant(VOTE1_ID, USER1_ID);
        assertMatch(vote, VOTE1);
        UserTestData.assertMatch(vote.getUser(),USER1);
        RestaurantTestData.assertMatch(vote.getRestaurant(),RESTAURANT3);
    }

    @Test
    public void getWithUserAndRestaurantNotExisted() {
        Assert.assertNull(voteRepository.getWithUserAndRestaurant(10, USER1_ID));
        Assert.assertNull(voteRepository.getWithUserAndRestaurant(VOTE1_ID, 10));
    }

    @Test
    public void getAll() {
        assertMatch(voteRepository.getAll(), VOTE1, VOTE2, VOTE3);
    }

    @Test
    public void getAllBetweenDates() {
        assertMatch(voteRepository.getAllBetweenDates(START_DATE, END_DATE), VOTE1, VOTE2);
    }

    @Test
    public void getByUserBetweenDates() {
        assertMatch(voteRepository.getByUserBetweenDates(START_DATE, END_DATE, USER1_ID), VOTE1);
    }

    @Test
    public void getByUserNotExisted() {
        assertMatch(voteRepository.getByUserBetweenDates(START_DATE, END_DATE, 10), Collections.emptyList());
    }

    @Test
    public void getByRestaurantBetweenDates() {
        assertMatch(voteRepository.getByRestaurantBetweenDates(START_DATE, END_DATE, RESTAURANT1_ID), VOTE2);
    }

    @Test
    public void getByRestaurantNotExisted() {
        assertMatch(voteRepository.getByRestaurantBetweenDates(START_DATE, END_DATE, 10), Collections.emptyList());
    }
}