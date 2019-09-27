/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viisitoistapeli;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.viisitoistapeli.GamePosition;

/**
 *
 * @author thoms
 */
public class GamePositionTest {

    @Test
    public void testMoveUp() {
        int[] field = {1, 0, 2, 3};
        GamePosition start = new GamePosition(field);
        GamePosition end = start.moveUp();
        int[] expected = {1, 3, 2, 0};
        Assert.assertArrayEquals(expected, end.getField());
    }

    @Test
    public void testMoveDown() {
        int[] field = {1, 2, 3, 0};
        GamePosition start = new GamePosition(field);
        GamePosition end = start.moveDown();
        int[] expected = {1, 0, 3, 2};
        Assert.assertArrayEquals(expected, end.getField());
    }

    @Test
    public void testMoveLeft() {
        int[] field = {1, 2, 0, 3};
        GamePosition start = new GamePosition(field);
        GamePosition end = start.moveLeft();
        int[] expected = {1, 2, 3, 0};
        Assert.assertArrayEquals(expected, end.getField());
    }

    @Test
    public void testMoveRight() {
        int[] field = {1, 2, 3, 0};
        GamePosition start = new GamePosition(field);
        GamePosition end = start.moveRight();
        int[] expected = {1, 2, 0, 3};
        Assert.assertArrayEquals(expected, end.getField());
    }

    @Test
    public void testCanMove() {
        int[] field = {1, 2, 3, 0};
        GamePosition start = new GamePosition(field);
        Assert.assertFalse(start.canMoveUp());
        Assert.assertTrue(start.canMoveDown());
        Assert.assertFalse(start.canMoveLeft());
        Assert.assertTrue(start.canMoveRight());
    }

    @Test
    public void testCalculateCostCorrectly() {
        int[] field = {0, 1, 2, 3};
        GamePosition position = new GamePosition(field);
        Assert.assertEquals(6, position.getCost());

    }
}
