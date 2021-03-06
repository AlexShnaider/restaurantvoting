package ru.restaurantvoting.repository;

import ru.restaurantvoting.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    //sorted by name, email
    List<User> getAll();
}