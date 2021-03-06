package ru.restaurantvoting.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurantvoting.model.Meal;
import ru.restaurantvoting.repository.MealRepository;
import ru.restaurantvoting.repository.crud.CrudMealRepository;
import ru.restaurantvoting.repository.crud.CrudRestaurantRepository;

import java.util.List;

@Repository
public class MealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudMealRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        return crudMealRepository.findById(id).filter(meal -> meal.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public Meal getWithRestaurant(int id, int restaurantId) {
        return crudMealRepository.getWithRestaurant(id, restaurantId);
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return crudMealRepository.getAll(restaurantId);
    }
}
