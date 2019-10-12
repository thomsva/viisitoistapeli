
package viisitoistapeli;

import org.junit.Assert;
import org.junit.Test;
import tiralabra.viisitoistapeli.GamePosition;
import tiralabra.viisitoistapeli.utility.GamePositionQueue;

public class GamePositionQueueTest {
    
    @Test
    public void testIsEmpty() {
        GamePositionQueue q = new GamePositionQueue();
        int[] numbers = {1,2,0,3};
        GamePosition g = new GamePosition(numbers);
        Assert.assertEquals(q.isEmpty(), true);
        q.add(g);
        Assert.assertEquals(q.isEmpty(), false);
    }
    
    @Test
    public void testSize() {
        GamePositionQueue q = new GamePositionQueue();
        int[] numbers = {1,2,0,3};
        GamePosition g1 = new GamePosition(numbers);
        GamePosition g2 = new GamePosition(numbers);
        Assert.assertEquals(0, q.size());
        q.add(g1);
        q.add(g2);
        Assert.assertEquals(2, q.size());
    }
    
    @Test
    public void testPoll() {
        GamePositionQueue q = new GamePositionQueue();
        int[] numbers1 = {1,2,0,3};
        int[] numbers2 = {1,2,3,0};
        int[] numbers3 = {2,0,1,3};
        GamePosition g1 = new GamePosition(numbers1);
        GamePosition g2 = new GamePosition(numbers2);
        GamePosition g3 = new GamePosition(numbers3);
        q.add(g1);
        q.add(g2);
        q.add(g3);
        Assert.assertEquals(g2, q.poll());
        Assert.assertEquals(g1, q.poll());
        Assert.assertEquals(g3, q.poll());
    }
}
