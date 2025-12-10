package org.example.vote;

import org.example.vote.model.Vote;
import org.example.vote.service.VoteService;
import org.example.vote.strategy.PluralityCountingStrategy;

public class CommandHandler {

    private final VoteService service;

    public CommandHandler(VoteService service) {
        this.service = service;
    }

    public String handle(String cmd, String voterId, String candidateId) {
        switch (cmd) {
            case "vote":
                service.cast(new Vote(voterId, candidateId, System.currentTimeMillis()));
                return "ok";

            case "count":
                return service.count(new PluralityCountingStrategy()).toString();

            case "reset":
                service.reset();
                return "reset";

            default:
                return "unknown";
        }
    }
}
