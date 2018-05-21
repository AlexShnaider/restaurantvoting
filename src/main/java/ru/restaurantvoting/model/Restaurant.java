package ru.restaurantvoting.model;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE m.id=:id")})
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {
    public static final String ALL_SORTED = "Meal.getAll";
    public static final String DELETE = "Meal.delete";

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Restaurant() {
        this.id = null;
    }

    public Restaurant(Integer id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Restaurant{id=" + id + '}';
    }
}
