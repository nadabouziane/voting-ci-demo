package org.example.vote.observer;

import org.example.vote.model.Vote;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoggingVoteListenerTest {

    @Test
    void testOnVotePrintsLog() {
        LoggingVoteListener listener = new LoggingVoteListener();
        Vote vote = new Vote("v1", "Alice", System.currentTimeMillis());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        listener.onVote(vote);

        String output = outContent.toString();
        assertTrue(output.contains("Vote received"));
        assertTrue(output.contains("Alice"));
    }
}
