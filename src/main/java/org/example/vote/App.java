package org.example.vote;

import org.example.vote.factory.RepositoryFactory;
import org.example.vote.model.Vote;
import org.example.vote.observer.LoggingVoteListener;
import org.example.vote.repo.VoteRepository;
import org.example.vote.service.VoteService;
import org.example.vote.strategy.PluralityCountingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private final VoteService voteService;
    private final Scanner scanner;

    public App() {
        VoteRepository repo = RepositoryFactory.createRepo("memory");
        this.voteService = new VoteService(repo);
        this.voteService.addListener(new LoggingVoteListener());
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        logger.info("Welcome to VotingApp! Type 'vote', 'count', 'reset', 'exit'");

        boolean running = true;
        while (running) {
            String cmd = scanner.nextLine().trim().toLowerCase();
            running = handleCommand(cmd);
        }

        scanner.close();
        logger.info("Bye!");
    }

    private boolean handleCommand(String command) {
        switch (command) {
            case "vote" -> handleVote();
            case "count" -> handleCount();
            case "reset" -> handleReset();
            case "exit" -> { return false; }
            default -> logger.warn("Unknown command");
        }
        return true;
    }

    private void handleVote() {
        logger.info("Enter Voter ID: ");
        String voterId = scanner.nextLine();

        logger.info("Enter Candidate ID: ");
        String candidateId = scanner.nextLine();

        voteService.cast(new Vote(voterId, candidateId, System.currentTimeMillis()));
        logger.info("Vote cast successfully!");
    }

    private void handleCount() {
        Map<String, Integer> results = voteService.count(new PluralityCountingStrategy());
        logger.info("Results: {}", results);
    }

    private void handleReset() {
        voteService.reset();
        logger.info("Reset done");
    }
}
