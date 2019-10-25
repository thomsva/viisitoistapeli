package tiralabra.viisitoistapeli.comparators;

import java.util.Comparator;
import java.util.Random;
import tiralabra.viisitoistapeli.GamePosition;

/**
 * Comparator for comparing the value of two GamePositions. 
 * 
 * This comparator primarily uses A* type of evaluation. If A* can't find a 
 * difference the comparator uses three different edge-finding heuristics. 
 * This comparator usually gives the solution faster than A* but the solution
 * is usually not a shortest path. 
 */
public class EdgesComparator implements Comparator<GamePosition> {

    
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
        if (g1.getCompletedLayers() > g2.getCompletedLayers()) {
            return -1;
        }
        if (g2.getCompletedLayers() > g1.getCompletedLayers()) {
            return 1;
        }

        int result = (g1.getManhattanDistance() + g1.getMoves()) - (g2.getManhattanDistance() + g2.getMoves());

        // Use edges distance if not enough edges are already completed
        if (g1.getCompletedLayers() <= (int) Math.sqrt(g1.getField().length - 2)) {
            if (result == 0) {
                result = (g1.getEdgesDistance() + g1.getMoves()) - (g2.getEdgesDistance() + g2.getMoves());
            }

            if (result == 0) {
                result = (g2.getEdgesStreak() - g1.getEdgesStreak());
            }

            if (result == 0) {
                result = g1.getEdgesPriority() - g2.getEdgesPriority();
            }
        }

        return result;
    }

}
