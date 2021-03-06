package ru.restaurantvoting.to;

import org.hibernate.validator.constraints.SafeHtml;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class RestaurantTo extends BaseTo implements Serializable {

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String name;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                " , name='" + name + '\'' +
                '}';
    }
}
