package ru.restaurantvoting;

import ru.restaurantvoting.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT0_ID = START_SEQ + 4;
    public static final int RESTAURANT1_ID = START_SEQ + 5;
    public static final int RESTAURANT2_ID = START_SEQ + 6;
    public static final int NEW_RESTAURANT_ID = START_SEQ + 13;

    public static final Restaurant RESTAURANT0 = new Restaurant(RESTAURANT0_ID, "Restaurant0");
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Restaurant1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "Restaurant2");
    public static final Restaurant NEW_RESTAURANT = new Restaurant(NEW_RESTAURANT_ID, "Restaurant new");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "meals");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("meals").isEqualTo(expected);
    }
}
