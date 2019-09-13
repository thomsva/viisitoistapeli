/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.viisitoistapeli;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author thomsva
 */
public class Viisitoistapeli {

    static HashMap<GamePosition, Integer> map = new HashMap<>(); //position, distance from start

    static String solve(GamePosition position, int moves, String path) {
        //System.out.println("map size: " + map.size());
        //System.out.println(Arrays.toString(position.field));
        String result = "";

        int[] solution = {1, 2, 3, 0};
        //int[] solution = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        //int[] solution = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0};
        
        

        boolean stop;

        if (map.containsKey(position)) {
            //been here
            if (map.get(position) > moves) {
                //already been here with more moves
                map.put(position, moves);
                //System.out.println("been here with more moves");
                stop = false;
            } else {
                //been here with less moves
                //System.out.println("been here with less moves");
                stop = true;
            }
        } else {
            //not been here
            //System.out.println("not been here");
            map.put(position, moves);
            stop = false;
        }

        if (Arrays.equals(position.field, solution)) {
            //solution found
            System.out.println("SOLUTION FOUND!!: " + path);
            stop = true;
        }

        if (!stop) {

            GamePosition positionUp = position.moveUp();
            GamePosition positionDown = position.moveDown();
            GamePosition positionLeft = position.moveLeft();
            GamePosition positionRight = position.moveRight();

            if (!position.equals(positionUp)) {
                //System.out.println("-going up from " + Arrays.toString(position.field));
                result = solve(positionUp, moves + 1, path + "U ");
            }
            if (!position.equals(positionDown)) {
                //System.out.println("-going down from " + Arrays.toString(position.field));
                result = solve(positionDown, moves + 1, path + "D ");
            }
            if (!position.equals(positionLeft)) {
                //System.out.println("-going left from " + Arrays.toString(position.field));
                result = solve(positionLeft, moves + 1, path + "L ");
            }
            if (!position.equals(positionRight)) {
                //System.out.println("-going right from " + Arrays.toString(position.field));
                result = solve(positionRight, moves + 1, path + "R ");
            }

        }

        return result;
    }

    public static void main(String[] args) {
        int[] field = {0, 1, 3, 2};
        //int[] field = {1, 2, 3, 4, 0, 5, 7, 8, 6};
        //int[] field = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0, 14};


        GamePosition start = new GamePosition(field);
        String solution = solve(start, 0, "");

        System.out.println("end");


    }

}
