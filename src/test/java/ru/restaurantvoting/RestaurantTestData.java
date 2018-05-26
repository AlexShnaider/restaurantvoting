package ru.restaurantvoting;

import ru.restaurantvoting.model.Meal;
import ru.restaurantvoting.model.Restaurant;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT0_ID = START_SEQ + 4;
    public static final int RESTAURANT1_ID = START_SEQ + 5;
    public static final int RESTAURANT2_ID = START_SEQ + 6;

    /*
    (100004, '2018-05-01 08:00:00', 'meat', 500),
  (100004, '2018-05-01 08:01:00', 'vegetables', 200),
  (100004, '2018-05-01 08:02:00', 'ice-cream', 100),
  (100005, '2018-05-01 08:03:00', 'business lunch', 500),
  (100006, '2018-05-01 08:04:00', 'fish soup', 300),
  (100006, '2018-05-01 08:05:00', 'pizza', 400);*/

    public static final Restaurant RESTAURANT0 = new Restaurant(RESTAURANT0_ID, "Restaurant0");
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Restaurant1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "Restaurant2");

    /*public static final List<Meal> MEALS = Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static Meal getCreated() {
        return new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Созданный ужин", 300);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL1.getDateTime(), "Обновленный завтрак", 200);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }*/
}
