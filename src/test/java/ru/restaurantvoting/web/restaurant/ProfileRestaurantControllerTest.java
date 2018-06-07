package ru.restaurantvoting.web.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.ResultActions;
import ru.restaurantvoting.model.Restaurant;
import ru.restaurantvoting.service.restaurant.RestaurantService;
import ru.restaurantvoting.util.exception.ErrorType;
import ru.restaurantvoting.web.json.JsonUtil;
import ru.restaurantvoting.web.user.AbstractControllerTest;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurantvoting.MealTestData.MEAL1;
import static ru.restaurantvoting.MealTestData.MEAL2;
import static ru.restaurantvoting.MealTestData.MEAL3;
import static ru.restaurantvoting.RestaurantTestData.*;
import static ru.restaurantvoting.TestUtil.*;
import static ru.restaurantvoting.UserTestData.ADMIN;
import static ru.restaurantvoting.UserTestData.USER1;

public class ProfileRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestaurantController.REST_URL + "/";

    @Autowired
    private RestaurantService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 10)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testGetWithMeals() throws Exception {
        Restaurant restaurantWithMeals = new Restaurant(RESTAURANT1);
        restaurantWithMeals.setMeals(Arrays.asList(MEAL1, MEAL2, MEAL3));
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID + "/detailed")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(restaurantWithMeals));
    }

    @Test
    public void testDeleteNotAllowed() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isMethodNotAllowed());
        assertMatch(service.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void testUpdateNotAllowed() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT2);
        updated.setName("updatedRestaurant2");

        mockMvc.perform(put(REST_URL + RESTAURANT2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER1)))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testCreateNotAllowed() throws Exception {
        Restaurant created = new Restaurant(NEW_RESTAURANT);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(USER1)))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(RESTAURANT1, RESTAURANT2, RESTAURANT3));
    }

}