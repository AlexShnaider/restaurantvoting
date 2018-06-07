package ru.restaurantvoting.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurantvoting.AuthorizedUser;
import ru.restaurantvoting.to.VoteTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileVoteController extends AbstractVoteController {

    static final String REST_URL = "/rest/profile";

    @GetMapping("/votes/{id}")
    public VoteTo get(@PathVariable("id") int id, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.get(id, authorizedUser.getId());
    }

    @DeleteMapping("/votes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.delete(id, authorizedUser.getId());
    }

    @PostMapping(value = "/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteTo> createWithLocation(@Validated @RequestBody VoteTo voteTo) {
        VoteTo created = super.create(voteTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/votes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VoteTo update(@Validated @RequestBody VoteTo voteTo, @PathVariable("id") int id
            , @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.update(voteTo, id, authorizedUser.getId());
    }

    @GetMapping(value = "/votes")
    public List<VoteTo> getByUser(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.getByUser(authorizedUser.getId());
    }

    @GetMapping(value = "/votes/today")
    public List<VoteTo> getByUserBetweenDates(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.getByUserBetweenDates(LocalDate.now(), LocalDate.now(), authorizedUser.getId());
    }
}