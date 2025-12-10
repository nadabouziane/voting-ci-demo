package org.example.vote.factory;

import org.example.vote.repo.InMemoryVoteRepository;
import org.example.vote.repo.VoteRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryFactoryTest {

    @Test
    void testCreateMemoryRepo() {
        VoteRepository repo = RepositoryFactory.createRepo("memory");
        assertNotNull(repo);
        assertTrue(repo instanceof InMemoryVoteRepository);
    }

    @Test
    void testCreateUnknownRepo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                RepositoryFactory.createRepo("unknown")
        );
        assertEquals("Unknown repo type unknown", exception.getMessage());
    }
}
