package tiralabra.viisitoistapeli;
import java.util.Arrays;
import java.util.PriorityQueue; // to be replaced with own data structure
import tiralabra.viisitoistapeli.utility.GamePositionQueue;
import tiralabra.viisitoistapeli.utility.IntegerQueue;

/**
 * The class is a helper class containing a search method used for 
 * solving the 15-puzzle.
 */
public class GameSolver {

    /**
     * A Star style of search algorithm.
     *
     * @param start represents the starting game position
     * @return a string containing the solution found by the search algorithm
     */
    static String search(GamePosition start) {
        //Establishing the solution of the game. The goal is to have all numbers
        //ordered with the empty spot (zero) as the last number.
        int[] solution = new int[start.getField().length];
        for(int i=0; i<solution.length-1;i++){
            solution[i]=i+1;
        }

        //Set up queue for next possible moves. The queue uses
        //GamePosition.compareTo for ranking.
        //PriorityQueue<GamePosition> queue = new PriorityQueue<>();
        GamePositionQueue queue = new GamePositionQueue();

        //Starting position can be reached with zero moves
        start.setMoves(0);

        //Add start position to queue before starting the search
        queue.add(start);

        while (!queue.isEmpty()) {
            
            //Poll the first GamePosition from the queue
            GamePosition current = queue.poll();

            //Check if goal is reached
            if (Arrays.equals(current.getField(), solution)) {
                System.out.println("Solution found " + current.getMoves() + " moves");
                System.out.println("Size of queue is: " + queue.size());
                return "The path from start to finish: " + current.getPath();
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
