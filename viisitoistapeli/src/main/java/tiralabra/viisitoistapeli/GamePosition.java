package tiralabra.viisitoistapeli;

import java.util.Arrays;

/**
 * The class represents an instance of a 15-puzzle playing field. The standard
 * size of the 15-puzzle is 4x4. However the class can also work with larger or
 * smaller fields provided that the field is square (2x2, 3x3, 4x4, 5x5 ...) The dddddddddddddddddd dddddddddddddd
 * size is determined by the array given to the constructor.
 */
public class GamePosition implements Comparable<GamePosition> {

    private final int[] field;
    private final int cost;
    private int moves;
    private GamePosition cameFrom;

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    /**
     * Creates an instance of the class GamePositiion
     *
     * @param f Array representing the playing field
     */
    public GamePosition(int[] f) {
        this.field = f;
        int x = (int) Math.sqrt(field.length); // size of playing field x*x
        //calculate cost
        int result = 0;
        for (int i = 0; i < field.length; i++) {
            int number = field[i];
            int rows, cols;
            if (number == 0) {
                rows = Math.abs(i / x - (x*x - 1) / x);
                cols = Math.abs(i % x - (x*x - 1) % x);
            } else {
                rows = Math.abs(i / x - (number - 1) / x);
                cols = Math.abs(i % x - (number - 1) % x);
            }
            result = result + rows + cols;
        }
        this.cost = result;
    }

    /**
     * Returns the array representing the playing field.
     * @return an array representing the playing field
     */
    public int[] getField() {
        return field;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "GamePosition{" + "field=" + Arrays.toString(field)
                + ", cost=" + cost
                + ", moves=" + moves + '}';
    }

    /**
     * Finds the zero in the field. 
     * @return the position of the zero
     */
    public int findZero() {
        for (int i = 0; i < this.field.length; i++) {
            if (this.field[i] == 0) {
                return i;
            }
        }
        return field.length + 1; //not found, should be considered an error!
    }

    public void setCameFrom(GamePosition cameFrom) {
        this.cameFrom = cameFrom;
    }

    public GamePosition getCameFrom() {
        return cameFrom;
    }

    public boolean canMoveDown() {
        int zero = this.findZero();
        int x = (int) Math.sqrt(this.field.length);
        return (zero > x);
    }

    public boolean canMoveUp() {
        int zero = this.findZero();
        int x = (int) Math.sqrt(this.field.length);
        return (zero < x * (x - 1));
    }

    public boolean canMoveRight() {
        int zero = this.findZero();
        int x = (int) Math.sqrt(this.field.length);
        return (zero % x > 0);
    }

    public boolean canMoveLeft() {
        int zero = this.findZero();
        int x = (int) Math.sqrt(this.field.length);
        return (zero % x < x - 1);
    }

    public GamePosition moveDown() {
        int[] f = this.field.clone();

        if (!this.canMoveDown()) {
            return this;
        }
        int zero = this.findZero();
        int x = (int) Math.sqrt(f.length);
        int move = zero - x;
        f[zero] = f[move];
        f[move] = 0;
        return new GamePosition(f);
    }

    public GamePosition moveUp() {
        int[] f = this.field.clone();
        if (!this.canMoveUp()) {
            return this;
        }
        int zero = this.findZero();
        int x = (int) Math.sqrt(f.length);
        int move = zero + x;
        f[zero] = f[move];
        f[move] = 0;
        return new GamePosition(f);
    }

    public GamePosition moveRight() {
        int[] f = this.field.clone();
        if (!this.canMoveRight()) {
            return this;
        }
        int zero = this.findZero();
        int move = zero - 1;
        f[zero] = f[move];
        f[move] = 0;
        return new GamePosition(f);
    }

    public GamePosition moveLeft() {
        int[] f = this.field.clone();

        if (!this.canMoveLeft()) {
            return this;
        }
        int zero = this.findZero();
        int move = zero + 1;
        f[zero] = f[move];
        f[move] = 0;
        return new GamePosition(f);
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
    public int compareTo(GamePosition o) {
        if(o==null) System.out.println("compareto: oh no it is null");

        return (this.cost + this.moves) - (o.getCost() + o.getMoves());
    }
}
