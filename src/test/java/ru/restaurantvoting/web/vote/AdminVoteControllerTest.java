package ru.restaurantvoting.web.vote;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.restaurantvoting.model.Vote;
import ru.restaurantvoting.service.vote.VoteService;
import ru.restaurantvoting.to.VoteTo;
import ru.restaurantvoting.util.exception.ErrorType;
import ru.restaurantvoting.web.json.JsonUtil;
import ru.restaurantvoting.web.AbstractControllerTest;

import java.time.LocalTime;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurantvoting.RestaurantTestData.RESTAURANT1_ID;
import static ru.restaurantvoting.RestaurantTestData.RESTAURANT2_ID;
import static ru.restaurantvoting.TestUtil.*;
import static ru.restaurantvoting.UserTestData.*;
import static ru.restaurantvoting.VoteTestData.*;
import static ru.restaurantvoting.util.VoteUtil.asTo;
import static ru.restaurantvoting.util.VoteUtil.createNewFromTo;

public class AdminVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminVoteController.REST_URL + "/";

    @Autowired
    private VoteService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + "users/" + USER1_ID + "/votes/" + VOTE1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(VOTE1));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + "users/" + USER1_ID + "/votes/" + VOTE1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + "users/" + USER1_ID + "/votes/" + 10)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(get(REST_URL + "users/" + 10 + "/votes/" + VOTE1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + "users/" + USER1_ID + "/votes/" + VOTE1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), VOTE2, VOTE3);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + "users/" + USER1_ID + "/votes/" + 10)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(delete(REST_URL + "users/" + 10 + "/votes/" + VOTE1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testDeleteForbidden() throws Exception {
        mockMvc.perform(delete(REST_URL + "users/" + USER1_ID + "/votes/" + VOTE1_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden());
        assertMatch(service.getAll(), VOTE1, VOTE2, VOTE3);
    }

    @Test
    public void testUpdate() throws Exception {
        int timeDifference = LocalTime.now().compareTo(LocalTime.of(11,0));
        Vote updated = new Vote(VOTE2);
        updated.setRestaurantId(RESTAURANT2_ID);

        mockMvc.perform(put(REST_URL + "users/" + USER2_ID + "/votes/" + VOTE2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(timeDifference < 0 ? status().isOk() : status().isUnprocessableEntity());

        assertMatch(service.get(VOTE2_ID, USER2_ID), timeDifference < 0 ? updated : VOTE2);
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Vote invalid = new Vote(null, null, null);
        mockMvc.perform(put(REST_URL + "users/" + USER2_ID + "/votes/" + VOTE2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        VoteTo created = asTo(new Vote(NEW_VOTE));
        ResultActions action = mockMvc.perform(post(REST_URL + "users/" + USER2_ID + "/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        VoteTo returned = readFromJson(action, VoteTo.class);
        created.setId(returned.getId());

        assertMatch(createNewFromTo(returned), createNewFromTo(created));
        assertMatch(service.getAll(), VOTE1, VOTE2, VOTE3, createNewFromTo(created));
    }

    @Test
    public void testCreateInvalid() throws Exception {
        Vote invalid = new Vote(null, null, null);
        mockMvc.perform(post(REST_URL + "users/" + USER2_ID + "/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + "votes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(VOTE1, VOTE2, VOTE3));
    }

    @Test
    public void testGetByUser() throws Exception {
        mockMvc.perform(get(REST_URL + "users/" + USER1_ID + "/votes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(Collections.singletonList(VOTE1)));
    }

    @Test
    public void testGetByUserBetweenDates() throws Exception {
        mockMvc.perform(get(REST_URL + "users/" + USER1_ID + "/votes/filter?startDate=2018-05-01&endDate=2018-05-01")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(Collections.singletonList(VOTE1)));

        mockMvc.perform(get(REST_URL + "users/" + USER1_ID + "/votes/filter?startDate=2018-05-02&endDate=2018-05-03")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(Collections.emptyList()));
    }

    @Test
    public void testGetByRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL + "restaurants/" + RESTAURANT1_ID + "/votes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(Collections.singletonList(VOTE2)));
    }

    @Test
    public void testGetByRestaurantBetweenDates() throws Exception {
        mockMvc.perform(get(REST_URL + "restaurants/" + RESTAURANT1_ID  + "/votes/filter?startDate=2018-05-01&endDate=2018-05-01")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(Collections.singletonList(VOTE2)));

        mockMvc.perform(get(REST_URL + "restaurants/" + RESTAURANT1_ID  + "/votes/filter?startDate=2018-05-02&endDate=2018-05-03")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(Collections.emptyList()));
    }
}