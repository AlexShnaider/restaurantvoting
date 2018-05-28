package ru.restaurantvoting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.user.id=:userId"),
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT v FROM Vote v ORDER BY v.dateTime, v.restaurant.id"),
        @NamedQuery(name = Vote.ALL_BETWEEN_DATES, query = "SELECT v FROM Vote v " +
                "WHERE v.dateTime BETWEEN :startDate AND :endDate ORDER BY v.dateTime"),
        @NamedQuery(name = Vote.GET_RESTAURANT_BETWEEN_DATES, query = "SELECT v FROM Vote v " +
                "WHERE v.restaurant.id=:restaurantId AND v.dateTime BETWEEN :startDate AND :endDate ORDER BY v.dateTime"),
        @NamedQuery(name = Vote.GET_USER_BETWEEN_DATES, query = "SELECT v FROM Vote v " +
                "WHERE v.user.id=:userId AND v.dateTime BETWEEN :startDate AND :endDate ORDER BY v.dateTime")
})
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity{
    public static final String ALL_SORTED = "Vote.getAll";
    public static final String ALL_BETWEEN_DATES = "Vote.getAllBetweenDates";
    public static final String GET_RESTAURANT_BETWEEN_DATES = "Vote.getByRestaurantBetweenDates";
    public static final String GET_USER_BETWEEN_DATES = "Vote.getByUserBetweenDates";
    public static final String DELETE = "Vote.delete";

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Vote() {
    }

    public Vote(LocalDateTime dateTime, User user, Restaurant restaurant) {
        this(null, dateTime, user, restaurant);
    }

    public Vote(Integer id, LocalDateTime dateTime, User user, Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "dateTime=" + dateTime +
                ", id=" + id +
                '}';
    }
}
