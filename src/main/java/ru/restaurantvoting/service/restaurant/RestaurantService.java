package ru.restaurantvoting.service.restaurant;

import ru.restaurantvoting.model.Restaurant;
import ru.restaurantvoting.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant meal);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    Restaurant update(Restaurant meal) throws NotFoundException;

    List<Restaurant> getAll();

    Restaurant getWithMeals(int id);
}