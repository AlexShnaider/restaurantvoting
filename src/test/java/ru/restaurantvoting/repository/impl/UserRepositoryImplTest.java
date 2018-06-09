package ru.restaurantvoting.repository.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.restaurantvoting.model.User;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static ru.restaurantvoting.UserTestData.*;

public class UserRepositoryImplTest extends BaseRepositoryImplTest{

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void save() {
        User newUser = new User(USER_NEW);
        User createdUser = userRepository.save(newUser);
        assertMatch(newUser, createdUser);
        assertMatch(userRepository.getAll(), ADMIN, USER1, USER2, USER3, newUser);
    }

    @Test
    public void delete() {
        assertTrue(userRepository.delete(USER1_ID));
        assertMatch(userRepository.getAll(), ADMIN, USER2, USER3);
    }

    @Test
    public void deleteNotExisted() {
        assertMatch(userRepository.getAll(), ADMIN, USER1, USER2, USER3);
        assertFalse(userRepository.delete(10));
        assertMatch(userRepository.getAll(), ADMIN, USER1, USER2, USER3);
    }

    @Test
    public void get() {
        User user = userRepository.get(USER1_ID);
        assertMatch(USER1, user);
        assertMatch(Arrays.asList(ADMIN, USER1, USER2, USER3), userRepository.getAll());
    }

    @Test
    public void getNotExisted() {
        assertNull(userRepository.get(10));
    }

    @Test
    public void getByEmail() {
        assertMatch(USER1, userRepository.getByEmail(USER1.getEmail()));
    }

    @Test
    public void getByEmailNotExisted() {
        assertNull(userRepository.getByEmail("notExisted@email.com"));
    }

    @Test
    public void getAll() {
        assertMatch(userRepository.getAll(), ADMIN, USER1, USER2, USER3);
    }
}