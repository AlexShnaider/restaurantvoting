package ru.restaurantvoting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.user.id=:userId"),
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT v FROM Vote v ORDER BY v.date, v.restaurant.id"),
        @NamedQuery(name = Vote.ALL_BETWEEN_DATES, query = "SELECT v FROM Vote v " +
                "WHERE v.date BETWEEN :startDate AND :endDate ORDER BY v.date"),
        @NamedQuery(name = Vote.GET_RESTAURANT_BETWEEN_DATES, query = "SELECT v FROM Vote v " +
                "WHERE v.restaurant.id=:restaurantId AND v.date BETWEEN :startDate AND :endDate ORDER BY v.date"),
        @NamedQuery(name = Vote.GET_USER_BETWEEN_DATES, query = "SELECT v FROM Vote v " +
                "WHERE v.user.id=:userId AND v.date BETWEEN :startDate AND :endDate ORDER BY v.date")
})
@Entity
@Table(name = "votes", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","date"}, name = "votes_unique_user_date_idx"))
public class Vote extends AbstractBaseEntity{
    public static final String ALL_SORTED = "Vote.getAll";
    public static final String ALL_BETWEEN_DATES = "Vote.getAllBetweenDates";
    public static final String GET_RESTAURANT_BETWEEN_DATES = "Vote.getByRestaurantBetweenDates";
    public static final String GET_USER_BETWEEN_DATES = "Vote.getByUserBetweenDates";
    public static final String DELETE = "Vote.delete";

    @Column(name = "date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "restaurant_id", insertable = false, updatable = false)
    //https://stackoverflow.com/a/6311954
    private Integer restaurantId;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    public Vote() {
    }

    public Vote(Vote vote) {
        this(vote.getId(), vote.getDate(),vote.user, vote.userId, vote.restaurant, vote.restaurantId);
    }

    public Vote(LocalDate date, Integer user, Integer restaurant) {
        this(null, date, user, restaurant);
    }

    public Vote(Integer id, LocalDate date, Integer userId, Integer restaurantId) {
        super(id);
        this.date = date;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public Vote(Integer id, LocalDate date, User user, Integer userId, Restaurant restaurant, Integer restaurantId) {
        super(id);
        this.date = date;
        this.user = user;
        this.userId = userId;
        this.restaurant = restaurant;
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "date=" + date +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
