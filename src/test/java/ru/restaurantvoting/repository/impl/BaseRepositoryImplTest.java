package ru.restaurantvoting.repository.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.restaurantvoting.repository.MealRepository;
import ru.restaurantvoting.repository.RestaurantRepository;
import ru.restaurantvoting.repository.UserRepository;
import ru.restaurantvoting.repository.VoteRepository;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract public class BaseRepositoryImplTest {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    MealRepository mealRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;
}