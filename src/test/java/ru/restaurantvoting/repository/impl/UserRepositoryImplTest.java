package ru.restaurantvoting.repository.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.restaurantvoting.model.User;
import ru.restaurantvoting.repository.UserRepository;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static ru.restaurantvoting.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserRepositoryImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void save() {
        User createdUser = userRepository.save(USER_NEW);
        assertMatch(USER_NEW, createdUser);
        assertMatch(userRepository.getAll(), ADMIN, USER0, USER1, USER2, USER_NEW);
    }

    @Test
    public void delete() {
        assertTrue(userRepository.delete(USER0_ID));
        assertMatch(userRepository.getAll(), ADMIN, USER1, USER2);
    }

    @Test
    public void deleteNotExisted() {
        assertMatch(userRepository.getAll(), ADMIN, USER0, USER1, USER2);
        assertFalse(userRepository.delete(10));
        assertMatch(userRepository.getAll(), ADMIN, USER0, USER1, USER2);
    }

    @Test
    public void get() {
        User user = userRepository.get(USER0_ID);
        assertMatch(USER0, user);
        assertMatch(Arrays.asList(ADMIN, USER0, USER1, USER2), userRepository.getAll());
    }

    @Test
    public void getNotExisted() {
        assertNull(userRepository.get(10));
    }

    @Test
    public void getByEmail() {
        assertMatch(USER0, userRepository.getByEmail(USER0.getEmail()));
    }

    @Test
    public void getByEmailNotExisted() {
        assertNull(userRepository.getByEmail("notExisted@email.com"));
    }

    @Test
    public void getAll() {
        assertMatch(userRepository.getAll(), ADMIN, USER0, USER1, USER2);
    }
}