package org.example.vote;

import org.example.vote.model.Vote;
import org.example.vote.service.VoteService;
import org.example.vote.strategy.PluralityCountingStrategy;

import java.util.Map;

public class CommandHandler {
    private final VoteService svc;

    public CommandHandler(VoteService svc) {
        this.svc = svc;
    }

    public String handle(String command, String voterId, String candidateId) {
        switch (command) {
            case "vote":
                svc.cast(new Vote(voterId, candidateId, System.currentTimeMillis()));
                return "ok";
            case "count":
                Map<String,Integer> res = svc.count(new PluralityCountingStrategy());
                return res.toString();
            case "reset":
                svc.reset();
                return "reset";
            default:
                return "unknown";
        }
    }
}
