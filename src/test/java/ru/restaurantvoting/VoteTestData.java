package ru.restaurantvoting;

import ru.restaurantvoting.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.restaurantvoting.RestaurantTestData.*;
import static ru.restaurantvoting.UserTestData.*;
import static ru.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE1_ID = START_SEQ + 13;
    public static final int VOTE2_ID = START_SEQ + 14;
    public static final int VOTE3_ID = START_SEQ + 15;

/*    public static final Vote VOTE1 = new Vote(VOTE1_ID, of(2018, Month.MAY, 1, 10, 0), USER1, RESTAURANT3);
    public static final Vote VOTE2 = new Vote(VOTE2_ID, of(2018, Month.MAY, 1, 10, 50), USER2, RESTAURANT1);
    public static final Vote VOTE3 = new Vote(VOTE3_ID, of(2018, Month.MAY, 2, 8, 20), ADMIN, RESTAURANT2);
    public static final Vote NEW_VOTE = new Vote(null, of(2018, Month.MAY, 2, 10, 0), USER2, RESTAURANT2);
    public static final LocalDateTime START_DATE = LocalDateTime.of(2018, Month.MAY, 1,0,0);
    public static final LocalDateTime END_DATE = LocalDateTime.of(2018, Month.MAY, 1,23,59);*/

    public static final Vote VOTE1 = new Vote(VOTE1_ID, LocalDate.of(2018, Month.MAY, 1), USER1_ID, RESTAURANT3_ID);
    public static final Vote VOTE2 = new Vote(VOTE2_ID, LocalDate.of(2018, Month.MAY ,1), USER2_ID, RESTAURANT1_ID);
    public static final Vote VOTE3 = new Vote(VOTE3_ID, LocalDate.of(2018, Month.MAY, 2), ADMIN_ID, RESTAURANT2_ID);
    public static final Vote NEW_VOTE = new Vote(null, LocalDate.of(2018, Month.MAY, 2),USER2, USER2_ID,RESTAURANT2, RESTAURANT2_ID);
    public static final LocalDate START_DATE = LocalDate.of(2018, Month.MAY, 1);
    public static final LocalDate END_DATE = LocalDate.of(2018, Month.MAY, 1);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "restaurant");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "restaurant").isEqualTo(expected);
    }
}
