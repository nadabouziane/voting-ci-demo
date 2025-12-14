package org.example.vote;

import org.example.vote.factory.RepositoryFactory;
import org.example.vote.observer.LoggingVoteListener;
import org.example.vote.repo.VoteRepository;
import org.example.vote.service.VoteService;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        VoteRepository repo = RepositoryFactory.createRepo("memory");
        VoteService svc = new VoteService(repo);
        svc.addListener(new LoggingVoteListener());

        CommandHandler handler = new CommandHandler(svc);

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to VotingApp! Type 'vote', 'count', 'reset', 'exit'");

        while (true) {
            String cmd = sc.nextLine();

            switch (cmd) {
                case "vote":
                    System.out.println("Voter ID:");
                    String voterId = sc.nextLine();
                    System.out.println("Candidate ID:");
                    String candidateId = sc.nextLine();
                    System.out.println(handler.handle("vote", voterId, candidateId));
                    break;

                case "count":
                    System.out.println(handler.handle("count", null, null));
                    break;

                case "reset":
                    System.out.println(handler.handle("reset", null, null));
                    break;

                case "exit":
                    System.out.println("Bye!");
                    sc.close();
                    return;

                default:
                    System.out.println(handler.handle(cmd, null, null));
            }
        }
    }
}
