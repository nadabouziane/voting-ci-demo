package org.example.vote.service;

import org.example.vote.model.Vote;
import org.example.vote.repo.InMemoryVoteRepository;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class VoteListenerTest {

    @Test
    void testListenerIsCalled() {
        VoteService svc = new VoteService(new InMemoryVoteRepository());
        AtomicBoolean called = new AtomicBoolean(false);

        svc.addListener(v -> called.set(true));

        svc.cast(new Vote("v1", "c1", 1));

        assertTrue(called.get(), "Listener should be notified on vote");
    }
}
