package ru.restaurantvoting.util;

import ru.restaurantvoting.model.Restaurant;
import ru.restaurantvoting.model.User;
import ru.restaurantvoting.model.Vote;
import ru.restaurantvoting.to.VoteTo;

import java.util.List;
import java.util.stream.Collectors;

public class VoteUtil {

    public static Vote createNewFromTo(VoteTo newVote) {
        return new Vote(newVote.getId(), newVote.getDate(), new User(newVote.getUserId()), newVote.getUserId()
                , new Restaurant(newVote.getRestaurantId()), newVote.getRestaurantId());
    }

    public static VoteTo asTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getDate(), vote.getUserId(), vote.getRestaurantId());
    }

    public static List<VoteTo> asTo(List<Vote> votes) {
        return votes.stream()
                .map(v -> new VoteTo(v.getId(), v.getDate(), v.getUserId(), v.getRestaurantId()))
                .collect(Collectors.toList());
    }

    public static Vote updateFromTo(Vote vote, VoteTo voteTo) {
        vote.setUser(new User(voteTo.getUserId()));
        vote.setUserId(voteTo.getUserId());
        vote.setRestaurant(new Restaurant(voteTo.getRestaurantId()));
        vote.setRestaurantId(voteTo.getRestaurantId());
        vote.setDate(voteTo.getDate());
        return vote;
    }
}