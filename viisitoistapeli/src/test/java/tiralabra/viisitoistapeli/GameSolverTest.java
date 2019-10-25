package tiralabra.viisitoistapeli;

import java.util.Comparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.viisitoistapeli.comparators.AStarComparator;
import tiralabra.viisitoistapeli.utility.GamePositionQueue;

public class GameSolverTest {

    @Test
    public void testSearch() {
        int[] numbers = {0, 2, 1, 3};
        GamePosition g = new GamePosition(numbers);
        String s = GameSolver.search(g, new AStarComparator(), 1000, Boolean.FALSE);
        Assert.assertEquals("UL", s);
    }

}
