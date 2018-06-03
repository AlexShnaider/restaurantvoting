package ru.restaurantvoting;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.restaurantvoting.model.Role;
import ru.restaurantvoting.model.User;
import ru.restaurantvoting.web.json.JsonUtil;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.restaurantvoting.model.AbstractBaseEntity.START_SEQ;
import static ru.restaurantvoting.web.json.JsonUtil.writeIgnoreProps;

public class UserTestData {
    public static final int USER1_ID = START_SEQ;
    public static final int USER2_ID = START_SEQ + 1;
    public static final int USER3_ID = START_SEQ + 2;
    public static final int ADMIN_ID = START_SEQ + 3;
    public static final int USER_NEW_ID = START_SEQ + 12;

    public static final User USER1 = new User(USER1_ID, "User1", "user1@yandex.ru", "password1", Role.ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "User2", "user2@yandex.ru", "password2", Role.ROLE_USER);
    public static final User USER3 = new User(USER3_ID, "User3", "user3@yandex.ru", "password3", Role.ROLE_USER);
    public static final User USER_NEW = new User(null, "UserNew", "userNew@yandex.ru", "passwordNew", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "meals", "password").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "password"));
    }

    public static ResultMatcher contentJson(User expected) {
        return content().json(writeIgnoreProps(expected, "password"));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}