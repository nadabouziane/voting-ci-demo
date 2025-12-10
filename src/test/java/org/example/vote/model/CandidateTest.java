package org.example.vote.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {
    @Test
    void testCandidate() {
        Candidate c = new Candidate("1", "Alice");
        assertEquals("1", c.id());
        assertEquals("Alice", c.name());
    }
}
