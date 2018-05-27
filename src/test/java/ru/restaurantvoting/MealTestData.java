package ru.restaurantvoting;

import ru.restaurantvoting.model.Meal;

import java.time.Month;
import java.util.Arrays;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 7;
    public static final int ADMIN_MEAL_ID = START_SEQ + 8;

    //Restaurant0 id=100004
    public static final Meal MEAL1 = new Meal(MEAL1_ID, of(2018, Month.MAY, 1, 8, 0), "meat", 500);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, of(2018, Month.MAY, 1, 8, 1), "vegetables", 200);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, of(2018, Month.MAY, 1, 8, 2), "ice-cream", 100);
    //Restaurant1 id=100005
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, of(2018, Month.MAY, 1, 8, 3), "business lunch", 500);
    //Restaurant2 id=100006
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, of(2018, Month.MAY, 1, 8, 4), "fish soup", 300);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, of(2018, Month.MAY, 1, 8, 5), "pizza", 400);

    public static final Meal NEW_MEAL = new Meal(null, of(2018, Month.MAY, 2, 10, 0), "new dish", 300);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
