package ru.restaurantvoting.util;

import ru.restaurantvoting.model.Meal;
import ru.restaurantvoting.to.MealTo;

import java.util.List;
import java.util.stream.Collectors;

public class MealUtil {

    public static Meal createNewFromTo(MealTo newMeal) {
        return new Meal(null, newMeal.getDateTime(), newMeal.getName(), newMeal.getPrice());
    }

    public static MealTo asTo(Meal meal) {
        return new MealTo(meal.getId(), meal.getName(), meal.getDateTime(), meal.getPrice());
    }

    public static List<MealTo> asTo(List<Meal> meals) {
        return meals.stream()
                .map(m -> new MealTo(m.getId(), m.getName(), m.getDateTime(), m.getPrice()))
                .collect(Collectors.toList());
    }

    public static Meal updateFromTo(Meal meal, MealTo mealTo) {
        meal.setName(mealTo.getName());
        meal.setDateTime(mealTo.getDateTime());
        meal.setPrice(mealTo.getPrice());
        return meal;
    }
}