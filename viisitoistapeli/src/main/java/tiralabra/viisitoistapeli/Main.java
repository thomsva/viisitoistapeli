package tiralabra.viisitoistapeli;

import java.util.Arrays;
import java.util.Scanner;
import static tiralabra.viisitoistapeli.GameSolver.search;

public class Main {

    /**
     * The use of the search algorithm is currently hard coded in the main
     * method.
     *
     * @param args
     */
    private static void printField(int[] field) {
        System.out.print("--------------------------------------");
        int x = (int) Math.sqrt(field.length); // size of playing field x*x
        for (int i = 0; i < x; i++) {
            System.out.println();
            for (int j = 0; j < x; j++) {
                if (field[i * x + j] < 10) {
                    System.out.print(" ");
                }
                if (field[i * x + j] < 100) {
                    System.out.print(" ");
                }
                System.out.print(field[i * x + j] + " ");
            }
        }
        System.out.println();
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {

        String command = "";
        int size = 4; //default size of game: 3*3
        int mix = 75; // should be 0
        int[] numbers = new int[size * size];
        for (int i = 0; i < numbers.length - 1; i++) {
            numbers[i] = i + 1;
        }
        GamePosition start = new GamePosition(numbers);
        start.setMoves(0);

        while (!command.equals("exit") && !command.equals("5")) {
            System.out.println();
            printField(start.getField());
            System.out.println("Settings: Game size " + size + "*" + size + ", mix: " + mix + ".");
            System.out.println("Available commands:");
            System.out.println("(1) size: Initialize game to a new size.");
            System.out.println("(2) mix: Mix the game.");
            System.out.println("(3) solve: Attempts to find solution for game.");
            System.out.println("(4) test: Run performance test and exit.");
            System.out.println("(5) exit: Exits program");
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();

            if (command.equals("size") || command.equals("1")) {
                System.out.println("");
                System.out.println("SIZE OF THE GAME");
                System.out.println("Initialize new game to a new size.");
                System.out.println("The current size is " + size + ".");
                System.out.print("Select new size: ");
                size = Integer.parseInt(sc.nextLine());
                numbers = new int[size * size];
                for (int i = 0; i < numbers.length - 1; i++) {
                    numbers[i] = i + 1;
                }
                start = new GamePosition(numbers);
                start.setMoves(0);
            }

            if (command.equals("mix") || command.equals("2")) {
                System.out.println("");
                System.out.println("MIXING GAME");
                System.out.print("Enter how many moves to mix: ");
                mix = Integer.parseInt(sc.nextLine());
                start.mix(mix);
                System.out.println("OK! Game mixed with " + mix + " moves.");
            }

            if (command.equals("solve") || command.equals("3")) {
                System.out.println("");
                System.out.println("GAME SOLVER");
                long timer = java.lang.System.currentTimeMillis();
                String solution = search(start);
                System.out.println("");
                System.out.println("Work time: " + (java.lang.System.currentTimeMillis() - timer) + " milliseconds");
                System.out.println(solution.length() + " moves");
                System.out.println("Solution: " + solution);
                System.out.println("D=down U=up R=right L=left");
                System.out.println("");
            }

            if (command.equals("test") || command.equals("4")) {
                int attempts=100;
                System.out.println("");
                System.out.println("RUNNING PERFORMANCE TESTS");
                System.out.println();
                System.out.println("Initializing new game sized " + size + "*" + size + ".");
                System.out.println("Solving puzzle "+attempts+" times.");

                numbers = new int[size * size];
                for (int i = 0; i < numbers.length - 1; i++) {
                    numbers[i] = i + 1;
                }
                start = new GamePosition(numbers);
                start.setMoves(0);
                boolean[] success = new boolean[attempts];
                long[] workTime = new long[attempts];
                long timer;
                for (int i = 0; i < 100; i++) {
                    if (mix == 0) {
                        start.mix(50);
                        System.out.print("Mixed " + 50 * (i + 1) + " moves. Finding solution... ");
                    } else {
                        numbers = new int[size * size];
                        for (int j = 0; j < numbers.length - 1; j++) {
                            numbers[j] = j + 1;
                        }
                        start = new GamePosition(numbers);
                        start.setMoves(0);
                        start.mix(mix);
                        System.out.print("Reinitialized and mixed " + mix + " moves. Finding solution... ");
                    }
                    System.gc();
                    timer = java.lang.System.currentTimeMillis();
                    String solution = search(start);
                    workTime[i] = (java.lang.System.currentTimeMillis() - timer);

                    if (solution.contains("Timeout")) {
                        System.out.print("timeout");
                        success[i] = false;
                    } else {
                        System.out.print(workTime[i] + " milliseconds (" + solution.length() + " moves)");
                        success[i] = true;
                    }
                    System.out.println();
                }
                System.out.println("Completed!");

                long totalTime = 0;
                int successCount = 0;
                for (int i = 0; i < attempts; i++) {
                    if (success[i]) {
                        totalTime = totalTime + workTime[i];
                        successCount++;
                    }
                }
                System.out.println(successCount + " successful attempts out of "+ attempts+".");
                System.out.println("Total work time: " + totalTime + " ms.");
                System.out.println("Average work time: " + totalTime / successCount + " ms.");
                command = "exit";
            }
        }
    }
}
