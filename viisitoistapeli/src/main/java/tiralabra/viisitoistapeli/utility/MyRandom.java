package tiralabra.viisitoistapeli.utility;

/**
 * A class for generating random numbers.
 */
public class MyRandom {

    long last;

    public MyRandom() {
        String str = String.valueOf(System.currentTimeMillis());
        str = str.substring(str.length() - 4);
        last = Integer.parseInt(str) % 4;
    }

    /**
     * The method generates random numbers between 0 to 3 and can be used for
     * representing each of four possible directions, up down left right.
     * @return  A random number between 0 and 3
     */
    public int Next0to3() {
        long result;
        result = (47059 * last + 11) % 47387;
        last = result;
        return (int) result % 4;
    }
}
