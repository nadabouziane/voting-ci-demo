package org.example.vote;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppMainTest {

    @Test
    void testMainFlow() {
        // Simule les entrées utilisateur : vote puis exit
        String input = "vote\nv1\nc1\ncount\nreset\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Exécute le main
        App.main(new String[]{});

        String output = outContent.toString();

        // Vérifie les messages attendus
        assertTrue(output.contains("Welcome to VotingApp"));
        assertTrue(output.contains("Voter ID:"));
        assertTrue(output.contains("Candidate ID:"));
        assertTrue(output.contains("Results:"));
        assertTrue(output.contains("Reset done"));
        assertTrue(output.contains("Bye!"));
    }
}
