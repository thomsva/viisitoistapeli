package tiralabra.viisitoistapeli;
import static tiralabra.viisitoistapeli.GameSolver.search;

public class Main {
    /**
     * The use of the search algorithm is currently hard coded in the main
     * method.
     *
     * @param args
     */
    public static void main(String[] args) {

        //int[] numbers = {5,1,8,  2,6,4,  0,3,7}; // 30 moves 
        //int[] numbers = {2,6,8,  4,1,3,  0,5,7}; // 16 moves 
        //int[] numbers = {5,4,1,  6,8,7,  2,3,0}; // 28 moves
        //int[] numbers = {1,2,3,  4,5,6,  7,0,8}; //1 move
        //int[] numbers = {2,5,3,  1,0,6,  4,7,8}; //6 moves
        int[] numbers = {5, 1, 0, 4,   6, 7, 3, 8,   2, 14, 10, 11,   9, 13, 15, 12}; // 20 moves
        //int[] numbers = {14, 4, 15, 0,   5, 7, 3, 1,   2, 6, 10, 11, 9, 13, 12, 8}; // 43 moves
        //int[] numbers = {1, 2, 3, 4,   5, 6, 7, 8,   9, 10 ,11, 12,   13, 14, 0, 15}; // 1 move

        GamePosition start = new GamePosition(numbers);
        System.out.println("start " + start);
        long timer = java.lang.System.currentTimeMillis();
        String solution = search(start);
        System.out.println("Work time: " + (java.lang.System.currentTimeMillis() - timer) + " milliseconds");
        System.out.println(solution);
        System.out.println("D=down U=up R=right L=left");
    }   
}
