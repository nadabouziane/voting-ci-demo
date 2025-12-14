package org.example.vote.service;

import org.example.vote.model.Vote;
import org.example.vote.observer.VoteListener;
import org.example.vote.repo.VoteRepository;
import org.example.vote.strategy.CountingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VoteService {

    private final VoteRepository repo;
    private final List<VoteListener> listeners = new ArrayList<>();

    public VoteService(VoteRepository repo) {
        this.repo = repo;
    }

    public void addListener(VoteListener listener) {
        listeners.add(listener);
    }

    public void cast(Vote vote) {
        repo.save(vote);
        for (VoteListener l : listeners) {
            l.onVote(vote);
        }
    }

    public Map<String, Integer> count(CountingStrategy strategy) {
        return strategy.count(repo.findAll());
    }

    public void reset() {
        repo.clear();
    }

    // 👇 IMPORTANT pour tes tests et la couverture
    public int countVotes() {
        return repo.findAll().size();
    }
}
