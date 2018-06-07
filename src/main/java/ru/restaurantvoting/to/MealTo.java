package ru.restaurantvoting.to;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

public class MealTo extends BaseTo implements Serializable {

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String name;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;

    @Range(min = 10, max = 5000)
    @NotNull
    private Integer price;

    public MealTo() {
    }

    public MealTo(Integer id, String name, LocalDateTime dateTime, Integer price) {
        super(id);
        this.name = name;
        this.dateTime = dateTime;
        this.price = price;
    }

    /*public static long getSerialVersionUID() {
        return serialVersionUID;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
