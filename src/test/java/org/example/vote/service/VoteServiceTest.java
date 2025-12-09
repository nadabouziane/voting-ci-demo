package org.example.vote.service;

import org.example.vote.repo.InMemoryVoteRepository;
import org.example.vote.model.Vote;
import org.example.vote.strategy.PluralityCountingStrategy;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoteServiceTest {
    @Test
    void testCastAndCount() {
        var repo = new InMemoryVoteRepository();
        var svc = new VoteService(repo);

        svc.cast(new Vote("v1","Alice", System.currentTimeMillis()));
        svc.cast(new Vote("v2","Alice", System.currentTimeMillis()));
        svc.cast(new Vote("v3","Bob", System.currentTimeMillis()));

        Map<String,Integer> res = svc.count(new PluralityCountingStrategy());
        assertEquals(2, res.get("Alice"));
        assertEquals(1, res.get("Bob"));
    }
}