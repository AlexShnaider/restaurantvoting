package ru.restaurantvoting.service.user;

import ru.restaurantvoting.model.User;
import ru.restaurantvoting.to.UserTo;
import ru.restaurantvoting.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    User update(User user);

    UserTo update(UserTo user);

    List<User> getAll();
}