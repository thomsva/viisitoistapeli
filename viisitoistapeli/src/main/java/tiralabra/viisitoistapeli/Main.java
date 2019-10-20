package tiralabra.viisitoistapeli;

import java.util.Scanner;
import static tiralabra.viisitoistapeli.GameSolver.search;

public class Main {

    /**
     * The use of the search algorithm is currently hard coded in the main
     * method.
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Game configuration");
        System.out.print("The size of the game is specified as x*x. Enter x: ");
        int size = Integer.parseInt(sc.nextLine());
        System.out.println("Game size selected " + size + "*" + size + ".");
        int[] numbers = new int[size * size];
        for (int i = 0; i < numbers.length - 1; i++) {
            numbers[i] = i + 1;
        }
        GamePosition start = new GamePosition(numbers);
        System.out.print("Mixing game. Enter how many moves to mix: ");
        int mix = Integer.parseInt(sc.nextLine());
        start.mix(mix);
        System.out.println("Mixed game: " + start);
        
        System.out.println("Press enter to start solver");
        sc.nextLine();
        long timer = java.lang.System.currentTimeMillis();
        String solution = search(start);
        System.out.println("Work time: " + (java.lang.System.currentTimeMillis() - timer) + " milliseconds");
        System.out.println(solution);
        System.out.println("D=down U=up R=right L=left");
    }
}
