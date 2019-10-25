
package tiralabra.viisitoistapeli.comparators;

import java.util.Comparator;
import tiralabra.viisitoistapeli.GamePosition;

/**
 * Comparator for comparing the value of two GamePositions. 
 * 
 * This comparator uses A* type of evaluation. 
 */
public class AStarComparator implements Comparator<GamePosition> {

    /**
     * Compares the values of two GamePositions. 
     *
     * @param   g1  GamePosition to compare
     * @param   g2  GamePosition to compare
     * @return      0 if the value of g1 and g2 are equal, a negative value
     * if g1 is preferable, a positive value if g2 is preferable.
     */
    @Override
    public int compare(GamePosition g1, GamePosition g2) {
        if (g1 == null || g2 == null) {
            return 0;
        }
        return (g1.getManhattanDistance() + g1.getMoves()) - (g2.getManhattanDistance() + g2.getMoves());
    }
}
