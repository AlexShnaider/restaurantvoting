package ru.restaurantvoting.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurantvoting.to.RestaurantTo;
import ru.restaurantvoting.to.RestaurantWithMealsTo;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/admin/restaurants";

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping("/{id}/detailed")
    public RestaurantWithMealsTo getWithMeals(@PathVariable("id") int id) {
        return super.getWithMeals(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        return super.getAll();
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantTo update(@Validated @RequestBody RestaurantTo restaurantTo, @PathVariable("id") int id) {
        return super.update(restaurantTo, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantTo> createWithLocation(@Validated @RequestBody RestaurantTo restaurantTo) {
        RestaurantTo created = super.create(restaurantTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}