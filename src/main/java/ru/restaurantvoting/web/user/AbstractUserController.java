package ru.restaurantvoting.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.restaurantvoting.model.User;
import ru.restaurantvoting.service.user.UserService;
import ru.restaurantvoting.to.UserTo;

import java.util.List;

import static ru.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.restaurantvoting.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public User update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        return service.update(user);
    }

    public UserTo update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        return service.update(userTo);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}