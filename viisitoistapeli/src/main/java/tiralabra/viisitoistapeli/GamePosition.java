package tiralabra.viisitoistapeli;

import java.util.Arrays;
import java.util.Random;
import tiralabra.viisitoistapeli.utility.MyRandom;

/**
 * The class represents an instance of a 15-puzzle playing field. The standard
 * size of the 15-puzzle is 4x4. However the class can also work with larger or
 * smaller fields provided that the field is square (2x2, 3x3, 4x4, 5x5 ...) The
 * playing field size is determined by the array given to the constructor.
 */
public class GamePosition {

    private final int[] field; // The array storing the playing field
    private int manhattanDistance;  // The distance for each number to their correct location
    private int edgesDistance; // The distance for the numbers making the top-left edges
    private int edgesPriority; // A measure for prioritizing the top left corner
    private int edgesStreak; // The number of correct numbers starting in the top-left corner
    private int moves; // The number of moves to the current position
    private GamePosition cameFrom; // The previous position.
    private String path = ""; // The path from the start as a String
    private int completedLayers = 0; // Number of complated layers starting from the top row and left column
    

    /**
     * Constructor. Creates an instance of the class GamePositiion
     *
     * @param f Array representing the playing field
     */
    public GamePosition(int[] f) {
        this.field = f;
        this.findManhattanDistance();
        this.findEdgesDistance();
        this.cameFrom = this;
    }

    /**
     * Finds the Manhattan distance for each numbers current location to it's
     * correct location. All distances are summed together and stored in a
     * class variable.
     */
    private void findManhattanDistance() {
        int x = (int) Math.sqrt(field.length); // size of playing field x*x
        int result = 0;
        for (int i = 0; i < field.length; i++) {
            int number = field[i];
            int rows = 0;
            int cols = 0;
            if (number != 0) {
                rows = Math.abs(i / x - (number - 1) / x);
                cols = Math.abs(i % x - (number - 1) % x);
                result = result + rows + cols;
            }
            this.manhattanDistance = result;
        }
    }

    /**
     * Finds the Edges distance for each numbers current location to it's
     * correct location. The calculation only takes into account the numbers 
     * belonging to the current layer of edges being built. The build starts 
     * from the top row and left column. When the first layer is complete the 
     * build continues to the second row and second column. 
     * All distances are summed together and stored in a
     * class variable.
     * In addition to the clean Edges-distance the method also calculates other
     * measures for differentiating between positions using an edges-first focus. 
     */
    private void findEdgesDistance() {
        int x = (int) Math.sqrt(field.length); // size of playing field x*x
        int result = 0;
        int weightedResult = 0;
        int streak = 0;
        boolean streakBroken = false;
        
        if (this.completedLayers >= x) {
            this.edgesDistance = 0;
            return;
        }

        for (int i = 0; i < field.length; i++) {
            boolean important = false; // true if the current number belongs to the edge-layer.
            int number = field[i];

            // Check if the current number is in the row being built
            for (int j = completedLayers + completedLayers * x; j < x + completedLayers * x; j++) {
                if (number == j + 1) {
                    important = true;
                }
            }
            // Check if the current number is in the column being built
            for (int j = completedLayers + completedLayers * x; j < field.length; j = j + x) {
                if (number == j + 1) {
                    important = true;
                }
            }
            
            if (important) {
                // The current number is a part of the edge
                int rows;
                int cols;
                if (number != 0) {
                    rows = Math.abs(i / x - (number - 1) / x);
                    cols = Math.abs(i % x - (number - 1) % x);
                    result = result + (rows + cols);
                    weightedResult = weightedResult + (rows + cols) * (field.length - number);
                    if ((rows + cols) == 0 && !streakBroken) {
                        streak++;
                    } else {
                        streakBroken = true;
                    }
                }

            }
        }
        if (result == 0) {
            // Yes! The layer is completed. Calculate distance for next layer. 
            this.completedLayers++;
            this.findEdgesDistance();
        }
        this.edgesStreak = streak;
        this.edgesPriority = weightedResult;
        this.edgesDistance = result;
    }

    /**
     * Returns the array representing the playing field.
     *
     * @return an array representing the playing field
     */
    public int[] getField() {
        return field;
    }

    /**
     *
     * @return The number of completed layers used by Edges comparator.
     */
    public int getCompletedLayers() {
        return completedLayers;
    }

    /**
     *
     * @return The Manhattan Distance used by the A* algortihm
     */
    public int getManhattanDistance() {
        return manhattanDistance;
    }

    /**
     *
     * @return The Edges Distance used by the Edges comparator
     */
    public int getEdgesDistance() {
        return edgesDistance;
    }

    /**
     *
     * @return A measure prioritizing low numbers for the Edges comparator
     */
    public int getEdgesPriority() {
        return edgesPriority;
    }

    /**
     *
     * @return The number of edge-pieces correctly placed starting from the top
     * left corner
     */
    public int getEdgesStreak() {
        return edgesStreak;
    }

    /**
     *
     * @return The number of moves to get to the current position.
     */
    public int getMoves() {
        return moves;
    }

    /**
     *
     * @param moves The number of moves to get to the current position.
     */
    public void setMoves(int moves) {
        this.moves = moves;
    }

    /**
     * Finds the zero in the field.
     *
     * @return The position of the zero.
     */
    public int findZero() {
        for (int i = 0; i < this.field.length; i++) {
            if (this.field[i] == 0) {
                return i;
            }
        }
        return 0;
    }

    /**
     *
     * @param cameFrom Previous game position.
     */
    public void setCameFrom(GamePosition cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     *
     * @return The previous game position.
     */
    public GamePosition getCameFrom() {
        return this.cameFrom;
    }

    /**
     * Set the path from the start to the current game position as a string. The
     * path is limited to 2000 characters to avoid problems when solving bigger
     * games.
     *
     * @param x The path to the current position from the start.
     */
    public void setPath(String x) {
        if (this.path.length() > 2000) {
            this.path = "Path cut when max length was exceeded... ";
        } else {
            this.path = x;
        }
    }

    /**
     *
     * @return The path to the current position from the start.
     */
    public String getPath() {
        return this.path;
    }

    /**
     *
     * @return True if it is possible to move down from this position.
     */
    public boolean canMoveDown() {
        int zero = this.findZero();
        int x = (int) Math.sqrt(this.field.length);
        //if ((zero / x) == completedLayers) {
        //    return false;
        //}
        return (zero > x);
    }

    /**
     *
     * @return True if it is possible to move up from this position.
     */
    public boolean canMoveUp() {
        int zero = this.findZero();
        int x = (int) Math.sqrt(this.field.length);
        return (zero < x * (x - 1));
    }

    /**
     *
     * @return True if it is possible to move right from this position.
     */
    public boolean canMoveRight() {
        int zero = this.findZero();
        int x = (int) Math.sqrt(this.field.length);
        //if ((zero % x) == completedLayers) {
        //    return false;
        //}
        return (zero % x > 0);
    }

    /**
     *
     * @return True if it is possible to move left from this position.
     */
    public boolean canMoveLeft() {
        int zero = this.findZero();
        int x = (int) Math.sqrt(this.field.length);
        return (zero % x < x - 1);
    }

    /**
     *
     * @return A new GamePosition, one move away from the current one. Returns
     * null if the move is not possible.
     */
    public GamePosition moveDown() {
        int[] f = this.field.clone();

        if (!this.canMoveDown()) {
            return null;
        }
        int zero = this.findZero();
        int x = (int) Math.sqrt(f.length);
        int move = zero - x;
        f[zero] = f[move];
        f[move] = 0;
        GamePosition newPos = new GamePosition(f);
        newPos.setCameFrom(this);
        newPos.setPath(this.path + "D");
        return newPos;
    }

    /**
     *
     * @return A new GamePosition, one move away from the current one. Returns
     * null if the move is not possible.
     */
    public GamePosition moveUp() {
        int[] f = this.field.clone();
        if (!this.canMoveUp()) {
            return null;
        }
        int zero = this.findZero();
        int x = (int) Math.sqrt(f.length);
        int move = zero + x;
        f[zero] = f[move];
        f[move] = 0;
        GamePosition newPos = new GamePosition(f);
        newPos.setCameFrom(this);
        newPos.setPath(this.path + "U");
        return newPos;
    }

    /**
     *
     * @return A new GamePosition, one move away from the current one. Returns
     * null if the move is not possible.
     */
    public GamePosition moveRight() {
        int[] f = this.field.clone();
        if (!this.canMoveRight()) {
            return null;
        }
        int zero = this.findZero();
        int move = zero - 1;
        f[zero] = f[move];
        f[move] = 0;
        GamePosition newPos = new GamePosition(f);
        newPos.setCameFrom(this);
        newPos.setPath(this.path + "R");
        return newPos;
    }

    /**
     *
     * @return A new GamePosition, one move away from the current one. Returns
     * null if the move is not possible.
     */
    public GamePosition moveLeft() {
        int[] f = this.field.clone();
        if (!this.canMoveLeft()) {
            return null;
        }
        int zero = this.findZero();
        int move = zero + 1;
        f[zero] = f[move];
        f[move] = 0;
        GamePosition newPos = new GamePosition(f);
        newPos.setCameFrom(this);
        newPos.setPath(this.path + "L");
        return newPos;
    }

    /**
     * A method used to randomly mix the playing field.
     *
     * @param n The number of moves to mix the field.
     * @return A String representing the path of the mixing operation.
     */
    public String mix(int n) {
        int i = 0;
        MyRandom rnd = new MyRandom();
        int x = (int) Math.sqrt(this.field.length);
        String result = "";

        while (i < n) {
            int direction = rnd.Next0to3(); // random 0 to 3
            int zero = this.findZero();

            // Find correct direction. Increment i only if direction is allowed
            if (direction == 0) {
                if (this.canMoveUp()) {
                    int move = zero + x;
                    this.field[zero] = this.field[move];
                    this.field[move] = 0;
                    result = result + "U";
                    i++;
                }
            }
            if (direction == 1) {
                if (this.canMoveDown()) {
                    int move = zero - x;
                    this.field[zero] = this.field[move];
                    this.field[move] = 0;
                    result = result + "D";
                    i++;
                }
            }
            if (direction == 2) {
                if (this.canMoveLeft()) {
                    int move = zero + 1;
                    this.field[zero] = this.field[move];
                    this.field[move] = 0;
                    result = result + "L";
                    i++;
                }
            }
            if (direction == 3) {
                if (this.canMoveRight()) {
                    int move = zero - 1;
                    this.field[zero] = this.field[move];
                    this.field[move] = 0;
                    result = result + "R";
                    i++;
                }
            }
        }
        // recalculate distances after mixing field
        this.findManhattanDistance();
        this.completedLayers = 0;
        this.findEdgesDistance();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof GamePosition)) {
            return false;
        }
        GamePosition otherGamePosition = (GamePosition) o;
        return Arrays.equals(otherGamePosition.field, this.field);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.field);
    }

    @Override
    public String toString() {
        return "GamePosition{" + "field=" + Arrays.toString(field)
                + ", manhattan=" + manhattanDistance
                + ", edges=" + edgesDistance
                + ", moves=" + moves + '}';
    }
}
