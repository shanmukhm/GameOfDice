package com.greatlearning.coding;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);

        Game game = new Game(N, M);
        game.start(scanner);

        scanner.close();
    }
}
