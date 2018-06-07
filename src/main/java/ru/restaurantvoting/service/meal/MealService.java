package ru.restaurantvoting.service.meal;

import ru.restaurantvoting.model.Meal;
import ru.restaurantvoting.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal create(Meal meal, int restaurantId);

    void delete(int id, int restaurantId) throws NotFoundException;

    Meal get(int id, int restaurantId) throws NotFoundException;

    Meal update(Meal meal, int restaurantId) throws NotFoundException;

    List<Meal> getAll(int restaurantId);

    Meal getWithRestaurant(int id, int restaurantId);
}