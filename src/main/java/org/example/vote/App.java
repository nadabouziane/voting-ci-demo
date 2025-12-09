package org.example.vote;

import org.example.vote.factory.RepositoryFactory;
import org.example.vote.model.Vote;
import org.example.vote.observer.LoggingVoteListener;
import org.example.vote.repo.VoteRepository;
import org.example.vote.service.VoteService;
import org.example.vote.strategy.PluralityCountingStrategy;

import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        VoteRepository repo = RepositoryFactory.createRepo("memory");
        VoteService svc = new VoteService(repo);
        svc.addListener(new LoggingVoteListener());

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to VotingApp! Type 'vote', 'count', 'reset', 'exit'");

        while(true){
            String cmd = sc.nextLine();
            switch(cmd){
                case "vote":
                    System.out.println("Voter ID:");
                    String voterId = sc.nextLine();
                    System.out.println("Candidate ID:");
                    String candidateId = sc.nextLine();
                    svc.cast(new Vote(voterId, candidateId, System.currentTimeMillis()));
                    break;
                case "count":
                    Map<String,Integer> res = svc.count(new PluralityCountingStrategy());
                    System.out.println("Results: " + res);
                    break;
                case "reset":
                    svc.reset();
                    System.out.println("Reset done");
                    break;
                case "exit":
                    System.out.println("Bye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
}