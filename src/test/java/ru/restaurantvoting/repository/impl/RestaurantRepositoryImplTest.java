package ru.restaurantvoting.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import ru.restaurantvoting.MealTestData;
import ru.restaurantvoting.model.Restaurant;

import static ru.restaurantvoting.MealTestData.MEAL1;
import static ru.restaurantvoting.MealTestData.MEAL2;
import static ru.restaurantvoting.MealTestData.MEAL3;
import static ru.restaurantvoting.RestaurantTestData.*;

public class RestaurantRepositoryImplTest extends BaseRepositoryImplTest{

    @Test
    public void save() {
        assertMatch(restaurantlRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
        Restaurant savedRestaurant = restaurantlRepository.save(NEW_RESTAURANT);
        assertMatch(savedRestaurant, NEW_RESTAURANT);
        assertMatch(restaurantlRepository.getAll(), NEW_RESTAURANT, RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void delete() {
        assertMatch(restaurantlRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
        Assert.assertTrue(restaurantlRepository.delete(RESTAURANT1_ID));
        assertMatch(restaurantlRepository.getAll(), RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void deleteNotExisted() {
        assertMatch(restaurantlRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
        Assert.assertFalse(restaurantlRepository.delete(10));
        assertMatch(restaurantlRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void get() {
        Restaurant restaurant = restaurantlRepository.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
        assertMatch(restaurantlRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void getNotExisted() {
        Assert.assertNull(restaurantlRepository.get(10));
        assertMatch(restaurantlRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void getAll() {
        assertMatch(restaurantlRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void getWithMeals() {
        Restaurant restaurant = restaurantlRepository.getWithMeals(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
        assertMatch(restaurantlRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
        MealTestData.assertMatch(restaurant.getMeals(), MEAL1, MEAL2, MEAL3);
    }
}