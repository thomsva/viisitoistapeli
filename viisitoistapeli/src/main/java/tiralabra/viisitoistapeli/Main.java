package tiralabra.viisitoistapeli;

import java.util.Comparator;
import java.util.Scanner;
import static tiralabra.viisitoistapeli.GameSolver.search;
import tiralabra.viisitoistapeli.comparators.AStarComparator;
import tiralabra.viisitoistapeli.comparators.DijkstraComparator;
import tiralabra.viisitoistapeli.comparators.EdgesComparator;

public class Main {

    /**
     * A simple UI for using the 15 puzzle search algorithms.
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
        int size = 4; //default size of game: 4*4
        long timeout = 1000; //default timeout 1 second
        int mix = 0;
        boolean randomBoost = true;
        Comparator comparator = new AStarComparator();
        String comparatorName = "A*";
        //int[] numbers = {6, 1, 12, 15, 0, 5, 3, 11, 2, 4, 9, 7, 14, 13, 8, 10};
        int[] numbers = new int[size * size];
        for (int i = 0; i < numbers.length - 1; i++) {
            numbers[i] = i + 1;
        }

        GamePosition start = new GamePosition(numbers);
        start.setMoves(0);

        while (!command.equals("exit") && !command.equals("8")) {
            System.out.println();
            printField(start.getField());
            System.out.println("Settings: Game size " + size + "*" + size
                    + ", mix: " + mix + ", method: " + comparatorName
                    + ", timeout: " + timeout + " ms");
            System.out.println("RandomBoost: " + randomBoost + "  (true = on, false = off)");
            System.out.println("Distances: Manhattan: " + start.getManhattanDistance()
                    + ", Edges: " + start.getEdgesDistance());
            System.out.println("Streak: " + start.getEdgesStreak());
            System.out.println("Available commands:");
            System.out.println("(1) size: Initialize game to a new size.");
            System.out.println("(2) mix: Mix the game.");
            System.out.println("(3) method: Select search algortihm type.");
            System.out.println("(4) solve: Attempts to find solution for game.");
            System.out.println("(5) test: Run performance tests (100 runs).");
            System.out.println("(6) timeout: Change timeout");
            System.out.println("(7) randomboost: Toggle on or off");
            System.out.println("(8) exit: Exits program");
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

            if (command.equals("method") || command.equals("3")) {
                System.out.println("");
                System.out.println("SELECT METHOD");
                System.out.println("Currently selected method: " + comparatorName);
                System.out.println("Available methods:");
                System.out.println();
                System.out.println("(1) dijkstra");
                System.out.println("(2) a*");
                System.out.println("(3) edges");
                System.out.print("Select new method: ");
                String method = sc.nextLine();
                if (method.equals("Dijkstra") || method.equals("1")) {
                    comparator = new DijkstraComparator();
                    comparatorName = "Dijkstra";
                }
                if (method.equals("A*") || method.equals("2")) {
                    comparator = new AStarComparator();
                    comparatorName = "A*";
                }
                if (method.equals("edges") || method.equals("3")) {
                    comparator = new EdgesComparator();
                    comparatorName = "Edges";
                }
            }

            if (command.equals("solve") || command.equals("4")) {
                System.out.println("");
                System.out.println("GAME SOLVER");
                System.out.println("Using " + comparatorName);
                System.out.println("! = random booster   . = edge found");
                long timer = java.lang.System.currentTimeMillis();
                String solution = search(start, comparator, timeout, randomBoost);
                System.out.println("");

                if (solution.contains("timeout")) {
                    System.out.println("timeout " + timeout + " milliseconds");
                } else {
                    System.out.println("Work time: "
                            + (java.lang.System.currentTimeMillis()
                            - timer) + " milliseconds");
                    System.out.print(solution.length() + " moves");
                }

                System.out.println("Solution: " + solution);
                System.out.println("D=down U=up R=right L=left");
                System.out.println("");
            }

            if (command.equals("test") || command.equals("5")) {
                int attempts = 100;
                System.out.println("");
                System.out.println("RUNNING PERFORMANCE TESTS");
                System.out.println();
                System.out.println("Initializing new game sized " + size + "*" + size + ".");
                System.out.println("Solving puzzle " + attempts + " times using "
                        + comparatorName + ".");

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
                        start.mix(20 * (i + 1));
                        System.out.print("Mixed " + (20 * (i + 1)) + " moves. Finding solution  ");
                    } else {
                        numbers = new int[size * size];
                        for (int j = 0; j < numbers.length - 1; j++) {
                            numbers[j] = j + 1;
                        }
                        start = new GamePosition(numbers);
                        start.setMoves(0);
                        start.mix(mix);
                        System.out.print("Reinitialized and mixed " + mix + " moves. Finding solution ");
                    }
                    System.gc();
                    timer = java.lang.System.currentTimeMillis();
                    String solution = search(start, comparator, timeout, randomBoost);
                    workTime[i] = (java.lang.System.currentTimeMillis() - timer);

                    if (solution.contains("timeout")) {
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
                System.out.println(successCount + " successful attempts out of " + attempts + ".");
                System.out.println("Total work time: " + totalTime + " ms.");
                if (successCount != 0) {
                    System.out.println("Average work time: " + totalTime / successCount + " ms.");
                }
            }
            if (command.equals("timeout") || command.equals("6")) {
                System.out.println("");
                System.out.println("SET TIMEOUT");
                System.out.print("Enter timeout in milliseconds: ");
                timeout = Integer.parseInt(sc.nextLine());
                System.out.println("OK! Timeout is now " + timeout + " milliseconds.");
            }

            if (command.equals("randomboost") || command.equals("7")) {
                System.out.println("");
                System.out.println("SET RANDOM BOOST");
                randomBoost = !randomBoost;
                System.out.println("OK! RandomBoost is now:  " + randomBoost);
                System.out.println("true = on, false = off");
            }
        }
    }
}
