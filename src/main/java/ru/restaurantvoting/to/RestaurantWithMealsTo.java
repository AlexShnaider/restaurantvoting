package ru.restaurantvoting.to;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class RestaurantWithMealsTo extends BaseTo implements Serializable {

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String name;

    List<MealTo> meals;

    public RestaurantWithMealsTo() {
    }

    public RestaurantWithMealsTo(Integer id, String name, List<MealTo> meals) {
        super(id);
        this.name = name;
        this.meals = meals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MealTo> getMeals() {
        return meals;
    }

    public void setMeals(List<MealTo> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "RestaurantWithMealsTo{" +
                "name='" + name + '\'' +
                ", meals=" + meals +
                ", id=" + id +
                '}';
    }
}
