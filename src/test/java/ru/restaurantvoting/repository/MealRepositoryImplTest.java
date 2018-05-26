package ru.restaurantvoting.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.restaurantvoting.model.Meal;

import java.util.Arrays;
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
        assertMatch(Collections.singletonList(MEAL4), mealRepository.getAll(RESTAURANT1_ID));
        Meal savedMeal = mealRepository.save(NEW_MEAL, RESTAURANT1_ID);
        assertMatch(savedMeal, NEW_MEAL);
        assertMatch(Arrays.asList(MEAL4, NEW_MEAL), mealRepository.getAll(RESTAURANT1_ID));
    }

    @Test
    public void delete() {
        assertMatch(Arrays.asList(MEAL1, MEAL2, MEAL3), mealRepository.getAll(RESTAURANT0_ID));
        Assert.assertTrue(mealRepository.delete(MEAL1_ID, RESTAURANT0_ID));
        assertMatch(Arrays.asList(MEAL2, MEAL3), mealRepository.getAll(RESTAURANT0_ID));
    }

    @Test
    public void deleteNotExisted() {
        assertMatch(Arrays.asList(MEAL1, MEAL2, MEAL3), mealRepository.getAll(RESTAURANT0_ID));
        Assert.assertFalse(mealRepository.delete(10, RESTAURANT0_ID));
        Assert.assertFalse(mealRepository.delete(MEAL1_ID, 10));
        assertMatch(Arrays.asList(MEAL1, MEAL2, MEAL3), mealRepository.getAll(RESTAURANT0_ID));
    }

    @Test
    public void get() {
        Meal meal = mealRepository.get(MEAL1_ID, RESTAURANT0_ID);
        assertMatch(meal, MEAL1);
        assertMatch(Arrays.asList(MEAL1, MEAL2, MEAL3), mealRepository.getAll(RESTAURANT0_ID));
    }

    @Test
    public void getNotExisted() {
        Assert.assertNull(mealRepository.get(10, RESTAURANT0_ID));
        Assert.assertNull(mealRepository.get(MEAL1_ID, 10));
    }

    @Test
    public void getAll() {
        assertMatch(Arrays.asList(MEAL1, MEAL2, MEAL3), mealRepository.getAll(RESTAURANT0_ID));
        assertMatch(Collections.singletonList(MEAL4), mealRepository.getAll(RESTAURANT1_ID));
        assertMatch(Arrays.asList(MEAL5, MEAL6), mealRepository.getAll(RESTAURANT2_ID));
    }

    @Test
    public void getAllNotExisted() {
        assertMatch(Collections.emptyList(), mealRepository.getAll(10));
    }
}