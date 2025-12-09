package org.example.vote.factory;

import org.example.vote.repo.*;

public class RepositoryFactory {
    public static VoteRepository createRepo(String type){
        if("memory".equalsIgnoreCase(type)) return new InMemoryVoteRepository();
        // future: file, jdbc
        throw new IllegalArgumentException("Unknown repo type "+type);
    }
}