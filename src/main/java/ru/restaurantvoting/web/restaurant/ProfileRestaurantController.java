package ru.restaurantvoting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.restaurantvoting.service.restaurant.RestaurantService;
import ru.restaurantvoting.to.RestaurantTo;
import ru.restaurantvoting.to.RestaurantWithMealsTo;

import java.util.List;

import static ru.restaurantvoting.util.RestaurantUtil.*;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/profile/restaurants";

    @Autowired
    private RestaurantService service;

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable("id") int id) {
        log.info("get restaurant {}", id);
        return asTo(service.get(id));
    }

    @GetMapping("/{id}/detailed")
    public RestaurantWithMealsTo getWithMeals(@PathVariable("id") int id) {
        log.info("get restaurant with meals {}", id);
        RestaurantWithMealsTo result = asWithMealsTo(service.getWithMeals(id));
        return result;
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return asTo(service.getAll());
    }
}