package tiralabra.viisitoistapeli;

import java.util.PriorityQueue;

/**
 * The class is used for solving the 15-puzzle.
 */
public class Viisitoistapeli {

    /**
     * A Star style of search algorithm
     * @param start Represents the starting game position
     */
    static String search(GamePosition start) {
        //Establishing the goal of the game
        int[] solution = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        GamePosition goal = new GamePosition(solution);
        
        //Set up queue for next possible moves. The queue uses 
        //GamePosition.compareTo for ranking. 
        PriorityQueue<GamePosition> queue = new PriorityQueue<>();
        
        //Starting position can be reached with zero moves
        start.setMoves(0);
       
        //Add start position to queue before starting the search
        queue.add(start);

        while (!queue.isEmpty()) {
            
            //Poll the first GamePosition from the queue 
            GamePosition current = queue.poll();

            //Check if goal is reached
            if (current.equals(goal)) {
                System.out.println("Solution found " + current.getMoves() + " moves");
                System.out.println("Size of queue is: " + queue.size());
                //Work in progress. The function should return the actual solution
                return "Goal reached!";
            }
            
            //Add all possible moves to queue. 
            if (current.canMoveUp()) {
                GamePosition up = current.moveUp();
                up.setMoves(current.getMoves() + 1);
                up.setCameFrom(current);
                queue.add(up);
            }
            if (current.canMoveDown()) {
                GamePosition down = current.moveDown();
                down.setMoves(current.getMoves() + 1);
                down.setCameFrom(current);
                queue.add(down);
            }
            if (current.canMoveLeft()) {
                GamePosition left = current.moveLeft();
                left.setMoves(current.getMoves() + 1);
                left.setCameFrom(current);
                queue.add(left);
            }
            if (current.canMoveRight()) {
                GamePosition right = current.moveRight();
                right.setMoves(current.getMoves() + 1);
                right.setCameFrom(current);
                queue.add(right);
            }
        }
        
        return ("Queue is empty but no solution was found ");
    }

    
    /**
     * Samples the use of the search algorithm.
     * @param args
     */
    public static void main(String[] args) {
        
        int[] numbers = {5, 1, 0, 4,
                        6, 7, 3, 8,
                        2, 14, 10, 11,
                        9, 13, 15, 12};

        GamePosition start = new GamePosition(numbers);

        System.out.println("start " + start);
        String solution = search(start);
        System.out.println(solution);

    }

}
