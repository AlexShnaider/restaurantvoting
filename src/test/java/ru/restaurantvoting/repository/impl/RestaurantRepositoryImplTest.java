package ru.restaurantvoting.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.restaurantvoting.MealTestData;
import ru.restaurantvoting.model.Restaurant;
import ru.restaurantvoting.repository.RestaurantlRepository;

import static ru.restaurantvoting.MealTestData.MEAL1;
import static ru.restaurantvoting.MealTestData.MEAL2;
import static ru.restaurantvoting.MealTestData.MEAL3;
import static ru.restaurantvoting.RestaurantTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantRepositoryImplTest {

    @Autowired
    RestaurantlRepository restaurantlRepository;

    @Test
    public void save() {
        assertMatch(restaurantlRepository.getAll(), RESTAURANT0, RESTAURANT1, RESTAURANT2);
        Restaurant savedRestaurant = restaurantlRepository.save(NEW_RESTAURANT);
        assertMatch(savedRestaurant, NEW_RESTAURANT);
        assertMatch(restaurantlRepository.getAll(), NEW_RESTAURANT, RESTAURANT0, RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void delete() {
        assertMatch(restaurantlRepository.getAll(), RESTAURANT0, RESTAURANT1, RESTAURANT2);
        Assert.assertTrue(restaurantlRepository.delete(RESTAURANT0_ID));
        assertMatch(restaurantlRepository.getAll(), RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void deleteNotExisted() {
        assertMatch(restaurantlRepository.getAll(), RESTAURANT0, RESTAURANT1, RESTAURANT2);
        Assert.assertFalse(restaurantlRepository.delete(10));
        assertMatch(restaurantlRepository.getAll(), RESTAURANT0, RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void get() {
        Restaurant restaurant = restaurantlRepository.get(RESTAURANT0_ID);
        assertMatch(restaurant, RESTAURANT0);
        assertMatch(restaurantlRepository.getAll(), RESTAURANT0, RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void getNotExisted() {
        Assert.assertNull(restaurantlRepository.get(10));
        assertMatch(restaurantlRepository.getAll(), RESTAURANT0, RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void getAll() {
        assertMatch(restaurantlRepository.getAll(), RESTAURANT0, RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void getWithMeals() {
        Restaurant restaurant = restaurantlRepository.getWithMeals(RESTAURANT0_ID);
        assertMatch(restaurant, RESTAURANT0);
        assertMatch(restaurantlRepository.getAll(), RESTAURANT0, RESTAURANT1, RESTAURANT2);
        MealTestData.assertMatch(restaurant.getMeals(), MEAL1, MEAL2, MEAL3);
    }
}