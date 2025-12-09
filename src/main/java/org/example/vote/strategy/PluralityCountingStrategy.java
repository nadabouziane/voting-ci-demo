package org.example.vote.strategy;

import org.example.vote.model.Vote;
import java.util.*;

public class PluralityCountingStrategy implements CountingStrategy {
    @Override
    public Map<String, Integer> count(List<Vote> votes){
        Map<String,Integer> m = new HashMap<>();
        for(Vote v: votes) m.merge(v.candidateId(), 1, Integer::sum);
        return m;
    }
}