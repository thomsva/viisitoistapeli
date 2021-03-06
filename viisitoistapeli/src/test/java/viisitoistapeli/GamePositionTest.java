
package viisitoistapeli;

import org.junit.Assert;
import org.junit.Test;
import tiralabra.viisitoistapeli.GamePosition;

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
    public void testCalculateManhattanDistanceOtherThanZero() {
        int[] field = {0, 1, 2, 3};
        GamePosition position = new GamePosition(field);
        Assert.assertNotEquals(0, position.getManhattanDistance());
    }
    
    @Test
    public void testSetAndGetMoves() {
        int[] field = {0, 1, 2, 3};
        GamePosition position = new GamePosition(field);
        position.setMoves(5);
        Assert.assertEquals(5, position.getMoves());
    }
    
        @Test
    public void testFindZero() {
        int[] field = {1, 2, 3, 0};
        GamePosition position = new GamePosition(field);
        Assert.assertEquals(3, position.findZero());
    }
    
}
