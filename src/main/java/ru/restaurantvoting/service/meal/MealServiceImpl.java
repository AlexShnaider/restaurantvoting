package ru.restaurantvoting.service.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.restaurantvoting.model.Meal;
import ru.restaurantvoting.repository.MealRepository;
import ru.restaurantvoting.util.exception.NotFoundException;

import java.util.List;

import static ru.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal create(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public Meal get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Override
    public Meal update(Meal meal, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(meal, restaurantId), meal.getId());
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public Meal getWithRestaurant(int id, int restaurantId) {
        return checkNotFoundWithId(repository.getWithRestaurant(id, restaurantId), id);
    }
}