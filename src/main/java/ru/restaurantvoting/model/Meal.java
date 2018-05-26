package ru.restaurantvoting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId ORDER BY m.id"),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.id=:restaurantId"),
        @NamedQuery(name = Meal.GET_BETWEEN, query = "SELECT m FROM Meal m " +
                "WHERE m.restaurant.id=:restaurantId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC"),
})
@Entity
@Table(name = "meals"/*, uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")}*/)
public class Meal extends AbstractNamedEntity {
    public static final String ALL_SORTED = "Meal.getAll";
    public static final String DELETE = "Meal.delete";
    public static final String GET_BETWEEN = "Meal.getBetween";

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 5000)
    @NotNull
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String name, int price) {
        this(null, dateTime, name, price);
    }

    public Meal(Integer id, LocalDateTime dateTime, String name, int price) {
        super(id, name);
        this.dateTime = dateTime;
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public int getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public void setPrice(Integer price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "dateTime=" + dateTime +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
