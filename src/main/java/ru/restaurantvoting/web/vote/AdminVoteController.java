package ru.restaurantvoting.web.vote;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurantvoting.to.VoteTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteController extends AbstractVoteController {

    static final String REST_URL = "/rest/admin";

    @GetMapping("/users/{userId}/votes/{id}")
    public VoteTo get(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        return super.get(id, userId);
    }

    @DeleteMapping("/users/{userId}/votes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        super.delete(id, userId);
    }

    @PostMapping(value = "/users/{userId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteTo> createWithLocation(@Validated @RequestBody VoteTo voteTo, @PathVariable("userId") int userId) {
        VoteTo created = super.create(voteTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/users/{userId}/votes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VoteTo update(@Validated @RequestBody VoteTo voteTo, @PathVariable("id") int id, @PathVariable("userId") int userId) {
        return super.update(voteTo, id, userId);
    }

    @GetMapping(value = "/votes")
    public List<VoteTo> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/votes/filter")
    public List<VoteTo> getAllBetweenDates(
            @RequestParam(value = "startDate", required = false) LocalDate startDate
            , @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return super.getAllBetweenDates(startDate, endDate);
    }

    @GetMapping(value = "/users/{userId}/votes")
    public List<VoteTo> getByUser(@PathVariable("userId") int userId) {
        return super.getByUser(userId);
    }

    @GetMapping(value = "/restaurants/{restaurantId}/votes")
    public List<VoteTo> getByRestaurant(@PathVariable("restaurantId") int restaurantId) {
        return super.getByRestaurant(restaurantId);
    }

    @GetMapping(value = "/users/{userId}/votes/filter")
    public List<VoteTo> getByUserBetweenDates(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
            , @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate endDate
            , @PathVariable("userId") int userId) {
        return super.getByUserBetweenDates(startDate,endDate,userId);
    }

    @GetMapping(value = "/restaurants/{restaurantId}/votes/filter")
    public List<VoteTo> getByRestaurantBetweenDates(
            @RequestParam(value = "startDate", required = false) LocalDate startDate
            , @RequestParam(value = "endDate", required = false) LocalDate endDate
            , @PathVariable("restaurantId") int restaurantId) {
        return super.getByRestaurantBetweenDates(startDate,endDate,restaurantId);
    }
}