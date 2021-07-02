package com.greatlearning.coding;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter number of players: ");
        int N = Integer.parseInt(args[0]);
//        System.out.println("Enter Target score: ");
        int M = Integer.parseInt(args[1]);

        Game game = new Game(N, M);
        game.start(scanner);

        scanner.close();
    }
}
