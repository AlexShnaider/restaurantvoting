package ru.restaurantvoting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurantvoting.model.Restaurant;
import ru.restaurantvoting.service.restaurant.RestaurantService;
import ru.restaurantvoting.to.RestaurantTo;
import ru.restaurantvoting.to.RestaurantWithMealsTo;

import java.util.List;

import static ru.restaurantvoting.util.RestaurantUtil.*;
import static ru.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.restaurantvoting.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public RestaurantTo get(int id) {
        log.info("get restaurant {}", id);
        return asTo(service.get(id));
    }

    public RestaurantWithMealsTo getWithMeals(int id) {
        log.info("get restaurant with meals {}", id);
        RestaurantWithMealsTo result = asWithMealsTo(service.getWithMeals(id));
        return result;
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return asTo(service.getAll());
    }

    /*public List<RestaurantTo> getAllWithTodayMeals() {
        log.info("getAll");
        return asTo(service.getAllWithTodayMeals());
    }*/

    @Transactional
    public RestaurantTo update(RestaurantTo restaurantTo, int id) {
        assureIdConsistent(restaurantTo, id);
        log.info("update {}", restaurantTo);
        Restaurant meal = updateFromTo(service.get(id), restaurantTo);
        return asTo(service.update(meal));
    }

    public RestaurantTo create(RestaurantTo restaurantTo) {
        log.info("create {}", restaurantTo);
        checkNew(restaurantTo);
        return asTo(service.create(createNewFromTo(restaurantTo)));
    }
}