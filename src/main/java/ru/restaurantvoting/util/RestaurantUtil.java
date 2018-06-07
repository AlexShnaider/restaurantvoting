package ru.restaurantvoting.util;

import ru.restaurantvoting.model.Restaurant;
import ru.restaurantvoting.to.RestaurantTo;
import ru.restaurantvoting.to.RestaurantWithMealsTo;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public static Restaurant createNewFromTo(RestaurantTo newRestaurant) {
        return new Restaurant(null, newRestaurant.getName());
    }

    public static RestaurantTo asTo(Restaurant meal) {
        return new RestaurantTo(meal.getId(), meal.getName());
    }

    public static RestaurantWithMealsTo asWithMealsTo(Restaurant meal) {
        return new RestaurantWithMealsTo(meal.getId(), meal.getName(), MealUtil.asTo(meal.getMeals()));
    }

    public static List<RestaurantTo> asTo(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(r -> new RestaurantTo(r.getId(), r.getName()))
                .collect(Collectors.toList());
    }

    public static Restaurant updateFromTo(Restaurant restaurant, RestaurantTo restaurantTo) {
        restaurant.setName(restaurantTo.getName());
        return restaurant;
    }
}