package org.example.vote;

import org.example.vote.repo.InMemoryVoteRepository;
import org.example.vote.service.VoteService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandHandlerTest {

    @Test
    void testVote() {
        VoteService svc = new VoteService(new InMemoryVoteRepository());
        CommandHandler handler = new CommandHandler(svc);

        String res = handler.handle("vote", "v1", "c1");
        assertEquals("ok", res);
        assertEquals(1, svc.countVotes());
    }

    @Test
    void testCount() {
        VoteService svc = new VoteService(new InMemoryVoteRepository());
        svc.cast(new org.example.vote.model.Vote("v1","c1",1));
        CommandHandler handler = new CommandHandler(svc);

        String res = handler.handle("count", null, null);
        assertTrue(res.contains("c1"));
    }

    @Test
    void testReset() {
        VoteService svc = new VoteService(new InMemoryVoteRepository());
        svc.cast(new org.example.vote.model.Vote("v1","c1",1));
        CommandHandler handler = new CommandHandler(svc);

        String res = handler.handle("reset", null, null);
        assertEquals("reset", res);
        assertEquals(0, svc.countVotes());
    }

    @Test
    void testUnknown() {
        VoteService svc = new VoteService(new InMemoryVoteRepository());
        CommandHandler handler = new CommandHandler(svc);

        String res = handler.handle("hello", null, null);
        assertEquals("unknown", res);
    }
}
