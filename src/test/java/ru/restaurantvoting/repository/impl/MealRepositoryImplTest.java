package ru.restaurantvoting.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import ru.restaurantvoting.RestaurantTestData;
import ru.restaurantvoting.model.Meal;
import java.util.Collections;

import static ru.restaurantvoting.MealTestData.*;
import static ru.restaurantvoting.RestaurantTestData.*;

public class MealRepositoryImplTest extends BaseRepositoryImplTest {

    @Test
    public void save() {
        assertMatch(mealRepository.getAll(RESTAURANT2_ID), MEAL4);
        Meal newMeal = new Meal(NEW_MEAL);
        Meal savedMeal = mealRepository.save(newMeal, RESTAURANT2_ID);
        assertMatch(savedMeal, newMeal);
        assertMatch(mealRepository.getAll(RESTAURANT2_ID), MEAL4, savedMeal);
    }

    @Test
    public void delete() {
        assertMatch(mealRepository.getAll(RESTAURANT1_ID), MEAL1, MEAL2, MEAL3);
        Assert.assertTrue(mealRepository.delete(MEAL1_ID, RESTAURANT1_ID));
        assertMatch(mealRepository.getAll(RESTAURANT1_ID), MEAL2, MEAL3);
    }

    @Test
    public void deleteNotExisted() {
        assertMatch(mealRepository.getAll(RESTAURANT1_ID), MEAL1, MEAL2, MEAL3);
        Assert.assertFalse(mealRepository.delete(10, RESTAURANT1_ID));
        Assert.assertFalse(mealRepository.delete(MEAL1_ID, 10));
        assertMatch(mealRepository.getAll(RESTAURANT1_ID), MEAL1, MEAL2, MEAL3);
    }

    @Test
    public void get() {
        Meal meal = mealRepository.get(MEAL1_ID, RESTAURANT1_ID);
        assertMatch(meal, MEAL1);
        assertMatch(mealRepository.getAll(RESTAURANT1_ID), MEAL1, MEAL2, MEAL3);
    }

    @Test
    public void getNotExisted() {
        Assert.assertNull(mealRepository.get(10, RESTAURANT1_ID));
        Assert.assertNull(mealRepository.get(MEAL1_ID, 10));
    }

    @Test
    public void getWithRestaurant() {
        Meal meal = mealRepository.getWithRestaurant(MEAL1_ID, RESTAURANT1_ID);
        assertMatch(meal, MEAL1);
        RestaurantTestData.assertMatch(meal.getRestaurant(), RESTAURANT1);
    }

    @Test
    public void getAll() {
        assertMatch(mealRepository.getAll(RESTAURANT1_ID), MEAL1, MEAL2, MEAL3);
        assertMatch(mealRepository.getAll(RESTAURANT2_ID), MEAL4);
        assertMatch(mealRepository.getAll(RESTAURANT3_ID), MEAL5, MEAL6);
    }

    @Test
    public void getAllNotExisted() {
        assertMatch(mealRepository.getAll(10), Collections.emptyList());
    }
}