package ru.restaurantvoting.repository;

import ru.restaurantvoting.model.Restaurant;

import java.util.List;

public interface RestaurantlRepository {
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    // null if not found
    Restaurant getWithMeals(int id);
}
