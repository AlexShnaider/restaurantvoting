package ru.restaurantvoting.repository;

import ru.restaurantvoting.model.Meal;

import java.util.List;

public interface MealRepository {
    // null if updated meal do not belong to restaurantId
    Meal save(Meal meal, int restaurantId);

    // false if meal do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    // null if meal do not belong to restaurantId
    Meal get(int id, int restaurantId);

    List<Meal> getAll(int restaurantId);

    // null if meal do not belong to restaurantId
    Meal getWithRestaurant(int id, int restaurantId);
}
