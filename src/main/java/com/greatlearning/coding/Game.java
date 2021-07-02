package com.greatlearning.coding;

import java.util.*;

public class Game {
    private final int noOfPlayers;
    private final int targetScore;

    private final Queue<Player> currentPlayersQ = new LinkedList<>();
    private final Queue<Player> completedPlayersQ = new LinkedList<>();
    private final ArrayList<Player> sortedPlayersList = new ArrayList<>();

    public Game(int noOfPlayers, int targetScore) {
        this.noOfPlayers = noOfPlayers;
        this.targetScore = targetScore;
    }

    public void start(Scanner scanner) {
        // Randomizing initial order of the players
        ArrayList<Integer> playingOrder = new ArrayList<>();
        for(int i=1; i <= noOfPlayers; i++) {
            playingOrder.add(i);
        }
        Collections.shuffle(playingOrder);

        StringBuilder sb = new StringBuilder();
        // Adding players to queue
        for(int playerNumber: playingOrder) {
            String playerName = "Player " + playerNumber;
            sb.append(playerName).append(", ");
            Player player = new Player(playerName);
            currentPlayersQ.add(player);
            sortedPlayersList.add(player);
        }

        System.out.println("Order of the players: ");
        System.out.println(sb.toString());

        // Actual game starts now...
        while (currentPlayersQ.size() > 1) {
            System.out.println();
            Player currentPlayer = currentPlayersQ.peek();
            if(currentPlayer.getSkipNext()) {
                System.out.println(currentPlayer.getName() + ", You are not allowed to roll the dice in this round.");
                currentPlayersQ.add(currentPlayersQ.poll());
                continue;
            }
            System.out.println(currentPlayer.getName() + ", Please press 'r' to roll the dice.");
            while (scanner.hasNext()) {
                if("r".equals(scanner.next())) {
                    break;
                }
            }

            Integer rolledValue = currentPlayer.roll();
            if(currentPlayer.getTotalScore() >= targetScore) {
                currentPlayer.setTotalScore(targetScore);
                currentPlayer.setRank(completedPlayersQ.size() + 1);
                completedPlayersQ.add(currentPlayersQ.poll());
                sortedPlayersList.remove(currentPlayer);
                System.out.println("Hey " + currentPlayer.getName()
                        + ", Congrats you completed the game. \nYour rank is " + currentPlayer.getRank());
            }

            // Sort by scores and assign ranks to the players
            sortedPlayersList.sort(Comparator.comparing(Player::getTotalScore).reversed());
            int rank = completedPlayersQ.size();
            System.out.println("-----------------------------");
            System.out.println("|  Name   |  Score  |  Rank  |");
            System.out.println("-----------------------------");
            for(Player p: sortedPlayersList) {
                p.setRank(++rank);
                p.print();
            }
            System.out.println("-----------------------------");

            // Removing current player from the queue and adding player to the queue again conditionally
            // If player rolls 6, player's position will be unchanged
            if(currentPlayer.getTotalScore() < targetScore && rolledValue < 6) {
                currentPlayersQ.add(currentPlayersQ.poll());
            }
        }

        System.out.println(currentPlayersQ.peek().getName() + ", You lost.");
        System.out.println("Game finished!");
        System.out.println();
        completedPlayersQ.add(currentPlayersQ.poll());

        System.out.println("-----------------------------");
        System.out.println("|  Name   |  Score  |  Rank  |");
        System.out.println("-----------------------------");
        for(Player p: completedPlayersQ) {
            p.print();
        }
        System.out.println("-----------------------------");
    }
}
