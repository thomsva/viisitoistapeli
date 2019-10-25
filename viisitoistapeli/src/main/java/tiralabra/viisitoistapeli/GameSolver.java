package tiralabra.viisitoistapeli;


import java.util.Arrays;
import java.util.Comparator;
import tiralabra.viisitoistapeli.utility.GamePositionQueue;
import tiralabra.viisitoistapeli.utility.MyRandom;

/**
 * The class is a helper class containing a search method used for solving the
 * 15-puzzle.
 */
public class GameSolver {

    /**
     * A search algorithm for solving a 15-puzzle.
     *
     * @param   start       The starting game position
     * @param   comparator  The comparator used for comparing game positions
     * @param   timeout     Timeout in milliseconds
     * @param   randomBoost Set to true if randomBoost is to be used
     * @return a string containing the solution found by the search algorithm
     */
    static String search(GamePosition start, Comparator comparator, long timeout, Boolean randomBoost) {
        //Establishing the solution of the game. The goal is to have all numbers
        //ordered with the empty spot (zero) as the last number.

        long timer = java.lang.System.currentTimeMillis();

        int[] solution = new int[start.getField().length];
        for (int i = 0; i < solution.length - 1; i++) {
            solution[i] = i + 1;
        }

        //Set up queue for next possible moves. The queue uses
        //the comparator for ranking.
        GamePositionQueue queue = new GamePositionQueue(comparator);

        //Starting position can be reached with zero moves
        start.setMoves(0);

        //Add start position to queue before starting the search
        queue.add(start);

        long count = 0; // Count the iterations
        long randomBoostCount = 0; // Count the times random boost is activated

        while (!queue.isEmpty()) {
            count++;

            // Poll the first GamePosition from the queue
            GamePosition current = queue.poll();

            // Check if goal is reached
            if (Arrays.equals(current.getField(), solution)) {
                //Goal reached
                return current.getPath();
            }

            // Check timeout
            if (java.lang.System.currentTimeMillis() - timer > timeout) {
                return ("timeout");
            }

            // RandomBooster: Do 10 random moves after each 5000 normal moves
            if (randomBoost && count > 5000) {
                System.out.print("!");
                count = 0;
                randomBoostCount++;
                if (randomBoostCount % 40 == 0) {
                    // Add line brake after printing ! 40 times
                    System.out.println();
                }
                queue.clear();
                queue.add(current);
                System.gc();
                MyRandom rnd = new MyRandom();
                for (int i = 0; i < 10; i++) {
                    GamePosition next = current;
                    int direction = rnd.Next0to3();
                    if (direction == 0) {
                        if (current.canMoveUp()) {
                            next = current.moveUp();
                            if (!current.getCameFrom().equals(next)) {
                                next.setMoves(current.getMoves() + 1);
                                queue.add(next);
                            }
                        }
                    }
                    if (direction == 1) {
                        if (current.canMoveDown()) {
                            next = current.moveDown();
                            if (!current.getCameFrom().equals(next)) {
                                next.setMoves(current.getMoves() + 1);
                                queue.add(next);
                            }
                        }
                    }
                    if (direction == 2) {
                        if (current.canMoveLeft()) {
                            next = current.moveLeft();
                            if (!current.getCameFrom().equals(next)) {
                                next.setMoves(current.getMoves() + 1);
                                queue.add(next);
                            }
                        }
                    }
                    if (direction == 3) {
                        if (current.canMoveRight()) {
                            next = current.moveRight();
                            if (!current.getCameFrom().equals(next)) {
                                next.setMoves(current.getMoves() + 1);
                                queue.add(next);
                            }
                        }

                    }
                    if (!next.equals(current)) {
                        current = next;
                    }
                }
            }

            //Add all possible moves to queue.
            if (current.canMoveUp()) {
                GamePosition up = current.moveUp();
                if (!current.getCameFrom().equals(up)) {
                    up.setMoves(current.getMoves() + 1);
                    queue.add(up);
                }
            }
            if (current.canMoveDown()) {
                GamePosition down = current.moveDown();
                if (!current.getCameFrom().equals(down)) {
                    down.setMoves(current.getMoves() + 1);
                    queue.add(down);
                }
            }
            if (current.canMoveLeft()) {
                GamePosition left = current.moveLeft();
                if (!current.getCameFrom().equals(left)) {
                    left.setMoves(current.getMoves() + 1);
                    queue.add(left);
                }
            }
            if (current.canMoveRight()) {
                GamePosition right = current.moveRight();
                if (!current.getCameFrom().equals(right)) {
                    right.setMoves(current.getMoves() + 1);
                    queue.add(right);
                }
            }
        }

        return ("Queue is empty but no solution was found ");
    }

}
