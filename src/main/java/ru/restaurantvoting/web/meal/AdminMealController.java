package ru.restaurantvoting.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurantvoting.model.Meal;
import ru.restaurantvoting.service.meal.MealService;
import ru.restaurantvoting.to.MealTo;

import java.net.URI;
import java.util.List;

import static ru.restaurantvoting.util.MealUtil.asTo;
import static ru.restaurantvoting.util.MealUtil.createNewFromTo;
import static ru.restaurantvoting.util.MealUtil.updateFromTo;
import static ru.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.restaurantvoting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMealController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    private MealService service;

    @GetMapping("/{restaurantId}/meals/{id}")
    public MealTo get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        log.info("get meal {} for restaurant {}", id, restaurantId);
        return asTo(service.get(id, restaurantId));
    }

    @DeleteMapping("/{restaurantId}/meals/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        log.info("delete meal {} for restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    @GetMapping("/{restaurantId}/meals")
    public List<MealTo> getAll(@PathVariable("restaurantId") int restaurantId) {
        log.info("getAll for restaurant {}", restaurantId);
        return asTo(service.getAll(restaurantId));
    }

    @Transactional
    @PutMapping(value = "/{restaurantId}/meals/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MealTo update(@Validated @RequestBody MealTo mealTo, @PathVariable("id") int id,
                       @PathVariable("restaurantId") int restaurantId) {
        assureIdConsistent(mealTo, id);
        log.info("update {} for restaurant {}", mealTo, restaurantId);
        Meal meal = updateFromTo(service.get(id, restaurantId), mealTo);
        return asTo(service.update(meal, restaurantId));
    }

    @PostMapping(value = "/{restaurantId}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MealTo> createWithLocation(@Validated @RequestBody MealTo mealTo,
                                                   @PathVariable("restaurantId") int restaurantId) {
        checkNew(mealTo);
        log.info("create {} for restaurant {}", mealTo, restaurantId);
        MealTo created = asTo(service.create(createNewFromTo(mealTo), restaurantId));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}