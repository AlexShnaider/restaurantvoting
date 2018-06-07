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
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
        Restaurant savedRestaurant = restaurantRepository.save(NEW_RESTAURANT);
        assertMatch(savedRestaurant, NEW_RESTAURANT);
        assertMatch(restaurantRepository.getAll(), NEW_RESTAURANT, RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void delete() {
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
        Assert.assertTrue(restaurantRepository.delete(RESTAURANT1_ID));
        assertMatch(restaurantRepository.getAll(), RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void deleteNotExisted() {
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
        Assert.assertFalse(restaurantRepository.delete(10));
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void get() {
        Restaurant restaurant = restaurantRepository.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void getNotExisted() {
        Assert.assertNull(restaurantRepository.get(10));
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void getAll() {
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void getWithMeals() {
        Restaurant restaurant = restaurantRepository.getWithMeals(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
        MealTestData.assertMatch(restaurant.getMeals(), MEAL1, MEAL2, MEAL3);
    }
}