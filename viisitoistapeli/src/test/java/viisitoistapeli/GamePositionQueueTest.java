package viisitoistapeli;

import org.junit.Assert;
import org.junit.Test;
import tiralabra.viisitoistapeli.GamePosition;
import tiralabra.viisitoistapeli.comparators.AStarComparator;
import tiralabra.viisitoistapeli.comparators.DijkstraComparator;
import tiralabra.viisitoistapeli.comparators.EdgesComparator;
import tiralabra.viisitoistapeli.utility.GamePositionQueue;

public class GamePositionQueueTest {

    @Test
    public void testQueueCanExpand() {
        GamePositionQueue q = new GamePositionQueue(new DijkstraComparator());
        int[] numbers = {1, 2, 0, 3};
        GamePosition g = new GamePosition(numbers);
        for (int i = 0; i < 1000; i++) {
            q.add(g);
        }
        Assert.assertEquals(1000, q.size());
        for (int i = 0; i < 1000; i++) {
            q.poll();
        }
        Assert.assertEquals(0, q.size());
    }

    @Test
    public void testComparators() {
        GamePositionQueue dijkstra = new GamePositionQueue(new DijkstraComparator());
        GamePositionQueue astar = new GamePositionQueue(new AStarComparator());
        GamePositionQueue edges = new GamePositionQueue(new EdgesComparator());
        int[] numbers1 = {1, 2, 3, 0};
        int[] numbers2 = {1, 2, 0, 3};
        int[] numbers3 = {0, 2, 1, 3};
        GamePosition g1 = new GamePosition(numbers1);
        GamePosition g2 = new GamePosition(numbers2);
        GamePosition g3 = new GamePosition(numbers3);
        dijkstra.add(g3);
        dijkstra.add(g2);
        dijkstra.add(g1);
        astar.add(g3);
        astar.add(g2);
        astar.add(g1);
        edges.add(g3);
        edges.add(g2);
        edges.add(g1);
        Assert.assertArrayEquals(dijkstra.poll().getField(), numbers1);
        Assert.assertArrayEquals(astar.poll().getField(), numbers1);
        Assert.assertArrayEquals(edges.poll().getField(), numbers1);
    }

    @Test
    public void testIsEmptySizeAndClear() {
        GamePositionQueue q = new GamePositionQueue(new AStarComparator());
        int[] numbers1 = {1, 2, 3, 0};
        int[] numbers2 = {1, 2, 0, 3};
        int[] numbers3 = {0, 2, 1, 3};
        GamePosition g1 = new GamePosition(numbers1);
        GamePosition g2 = new GamePosition(numbers2);
        GamePosition g3 = new GamePosition(numbers3);
        q.add(g3);
        q.add(g2);
        q.add(g1);
        Assert.assertEquals(3, q.size());
        Assert.assertFalse(q.isEmpty());
        q.clear();
        Assert.assertEquals(0, q.size());
        Assert.assertTrue(q.isEmpty());
    }

}
