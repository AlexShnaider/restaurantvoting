package ru.restaurantvoting.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.restaurantvoting.RestaurantTestData;
import ru.restaurantvoting.model.Meal;
import ru.restaurantvoting.repository.MealRepository;

import java.util.Collections;

import static ru.restaurantvoting.MealTestData.*;
import static ru.restaurantvoting.RestaurantTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealRepositoryImplTest {

    @Autowired
    MealRepository mealRepository;

    @Test
    public void save() {
        assertMatch(mealRepository.getAll(RESTAURANT1_ID), MEAL4);
        Meal savedMeal = mealRepository.save(NEW_MEAL, RESTAURANT1_ID);
        assertMatch(savedMeal, NEW_MEAL);
        assertMatch(mealRepository.getAll(RESTAURANT1_ID), MEAL4, NEW_MEAL);
    }

    @Test
    public void delete() {
        assertMatch(mealRepository.getAll(RESTAURANT0_ID), MEAL1, MEAL2, MEAL3);
        Assert.assertTrue(mealRepository.delete(MEAL1_ID, RESTAURANT0_ID));
        assertMatch(mealRepository.getAll(RESTAURANT0_ID), MEAL2, MEAL3);
    }

    @Test
    public void deleteNotExisted() {
        assertMatch(mealRepository.getAll(RESTAURANT0_ID), MEAL1, MEAL2, MEAL3);
        Assert.assertFalse(mealRepository.delete(10, RESTAURANT0_ID));
        Assert.assertFalse(mealRepository.delete(MEAL1_ID, 10));
        assertMatch(mealRepository.getAll(RESTAURANT0_ID), MEAL1, MEAL2, MEAL3);
    }

    @Test
    public void get() {
        Meal meal = mealRepository.get(MEAL1_ID, RESTAURANT0_ID);
        assertMatch(meal, MEAL1);
        assertMatch(mealRepository.getAll(RESTAURANT0_ID), MEAL1, MEAL2, MEAL3);
    }

    @Test
    public void getNotExisted() {
        Assert.assertNull(mealRepository.get(10, RESTAURANT0_ID));
        Assert.assertNull(mealRepository.get(MEAL1_ID, 10));
    }

    @Test
    public void getWithRestaurant() {
        Meal meal = mealRepository.getWithRestaurant(MEAL1_ID, RESTAURANT0_ID);
        assertMatch(meal, MEAL1);
        RestaurantTestData.assertMatch(meal.getRestaurant(), RESTAURANT0);
    }

    @Test
    public void getAll() {
        assertMatch(mealRepository.getAll(RESTAURANT0_ID), MEAL1, MEAL2, MEAL3);
        assertMatch(mealRepository.getAll(RESTAURANT1_ID), MEAL4);
        assertMatch(mealRepository.getAll(RESTAURANT2_ID), MEAL5, MEAL6);
    }

    @Test
    public void getAllNotExisted() {
        assertMatch(mealRepository.getAll(10), Collections.emptyList());
    }
}