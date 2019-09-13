/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.viisitoistapeli;

import java.util.Arrays;

/**
 * The class represents an instance of a 15-puzzle playing field. 
 * The standard size of the 15-puzzle is 4x4. However the class can also work 
 * with larger or smaller fields provided that the field is square 
 * (2x2, 3x3, 4x4, 5x5 ...)
 * The size is determined by the array stored in the parameter field. 
 */
public class GamePosition {

    int[] field;

    /**
     * Creates an instance of the class GamePosition
     * @param field array representing the playing field
     */
    public GamePosition(int[] field) {
        this.field = field;
    }

    /**
     * Returns the array representing the playing field
     */
    public int[] getField() {
        return field;
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
        GamePosition result = new GamePosition(f);
        if (!this.canMoveDown()) {
            return result;
        }
        int zero = this.findZero();
        int x = (int) Math.sqrt(f.length);
        int move = zero - x;
        f[zero] = f[move];
        f[move] = 0;
        result.setField(f);
        return result;
    }

    public GamePosition moveUp() {
        int[] f = this.field.clone();
        GamePosition result = new GamePosition(f);
        if (!this.canMoveUp()) {
            return result;
        }
        int zero = this.findZero();
        int x = (int) Math.sqrt(f.length);
        int move = zero + x;
        f[zero] = f[move];
        f[move] = 0;
        result.setField(f);
        return result;
    }

    public GamePosition moveRight() {
        int[] f = this.field.clone();
        GamePosition result = new GamePosition(f);
        if (!this.canMoveRight()) {
            return result;
        }
        int zero = this.findZero();
        int move = zero - 1;
        f[zero] = f[move];
        f[move] = 0;
        result.setField(f);
        return result;
    }

    public GamePosition moveLeft() {
        int[] f = this.field.clone();
        GamePosition result = new GamePosition(f);
        if (!this.canMoveLeft()) {
            return result;
        }
        int zero = this.findZero();
        int move = zero + 1;
        f[zero] = f[move];
        f[move] = 0;
        result.setField(f);
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
}
