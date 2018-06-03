package ru.restaurantvoting.service;

import ru.restaurantvoting.model.User;
import ru.restaurantvoting.to.UserTo;
import ru.restaurantvoting.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    void update(UserTo user);

    List<User> getAll();

    //User getWithMeals(int id);
}