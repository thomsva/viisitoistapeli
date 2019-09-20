package tiralabra.viisitoistapeli;

import java.util.Arrays;

/**
 * The class represents an instance of a 15-puzzle playing field. The standard
 * size of the 15-puzzle is 4x4. However the class can also work with larger or
 * smaller fields provided that the field is square (2x2, 3x3, 4x4, 5x5 ...) The
 * size is determined by the array stored in the parameter field.
 */
public class GamePosition {

    int[] field;
    int cost;

    /**
     * Creates an instance of the class GamePositiion
     *
     * @param f array representing the playing field
     */
    public GamePosition(int[] f) {
        this.field = f;
        int x = (int) Math.sqrt(field.length); // size of playing field x*x

        //calculate cost
        int result = 0;
        for (int i = 1; i <= field.length; i++) {
            int number = field[i - 1];
            int rows, cols;
            if (number == 0) {
                rows = Math.abs(((1+i) / x) - ((1+x*x) / x));  
                cols = Math.abs(i % x - (x*x) % x);        
            } else {
                rows = Math.abs(((1+i) / x) - ((1+number) / x));    
                cols = Math.abs(i % x - number % x);       
            }
            //System.out.println("i:" + i + " number:" + number + " rows:"+ rows + " cols:"+cols);
            result = result + rows + cols;
        }
        this.cost = result;

    }

    /**
     * Returns the array representing the playing field
     */
    public int[] getField() {
        return field;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "GamePosition{" + "field=" + Arrays.toString(field) + ", cost=" + cost + '}';
    }

    public void setField(int[] field) {
        this.field = field;
    }

    public int findZero() {
        for (int i = 0; i < this.field.length; i++) {
            if (this.field[i] == 0) {
                return i;
            }
        }
        return field.length + 1; //not found, should be considered an error!
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
}
