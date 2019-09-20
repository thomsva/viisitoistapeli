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

    // recursive "bredth first search" function
    static String solve(GamePosition position, int moves, String path) {
        //System.out.println("map size: " + map.size());
        //System.out.println(Arrays.toString(position.field));
        String result = path;

        int[] solution = {1, 2, 3, 0};
        //int[] solution = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        //int[] solution = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0};

        boolean stop;

        //check if the gameposition exists in the map
        if (map.containsKey(position)) {
            //been in this positopn
            if (map.get(position) > moves) {
                //been here with more moves
                map.put(position, moves);
                //System.out.println("been here with more moves");
                stop = false;
            } else {
                //been here with less moves
                //System.out.println("been here with less moves");
                stop = true;
            }
        } else {
            //new position found
            //System.out.println("not been here");
            map.put(position, moves); //add new position to map
            stop = false;
        }

        if (Arrays.equals(position.field, solution)) {
            //solution found
            System.out.println("SOLUTION FOUND!!: " + path);
            return path;
        }

        if (!stop) {
            //continue with new moves
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

    // A Star style of search algorithm, work in progress
    static String aStar(GamePosition position) {
        //int[] solution = {1, 2, 3, 0};
        int[] solution = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        //int[] solution = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0};
        int moves = 0;
        while (!Arrays.equals(position.getField(), solution)) {
            GamePosition positionUp = position.moveUp();
            GamePosition positionDown = position.moveDown();
            GamePosition positionLeft = position.moveLeft();
            GamePosition positionRight = position.moveRight();
            //System.out.println("--" + position);
            //System.out.println("up " + positionUp);
            //System.out.println("down " + positionDown);
            //System.out.println("left " + positionLeft);
            //System.out.println("right " + positionRight);
            if (positionUp.getCost() <= position.getCost() && !map.containsKey(positionUp)) {
                position = positionUp;
            }
            if (positionDown.getCost() <= position.getCost() && !map.containsKey(positionDown)) {
                position = positionDown;
            }
            if (positionLeft.getCost() <= position.getCost() && !map.containsKey(positionLeft)) {
                position = positionLeft;
            }
            if (positionRight.getCost() <= position.getCost() && !map.containsKey(positionRight)) {
                position = positionRight;
            }
            map.put(position, moves);
            moves++;
            //System.out.println("current position:" + position);
        }
        System.out.println("moves: " + moves);
        return Arrays.toString(position.getField());

    }

    public static void main(String[] args) {
        //int[] field = {3, 1, 0, 2};

        //int[] field = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        int[] field = {1, 0, 2, 4, 5, 3, 7, 8, 6};
        //int[] field = {2,1,4,5,3,8,7,6,0};
        //int[] field = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0, 14};

        GamePosition start = new GamePosition(field);
        System.out.println("start " + start);
        map.put(start, 0);

        //String solution = solve(start, 0, "");
        String solution = aStar(start);
        System.out.println("solution: " + solution);

        System.out.println("end");

    }

}
