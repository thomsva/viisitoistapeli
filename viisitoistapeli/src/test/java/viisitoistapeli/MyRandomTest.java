package viisitoistapeli;

import org.junit.Assert;
import org.junit.Test;
import tiralabra.viisitoistapeli.utility.MyRandom;

public class MyRandomTest {
    
   @Test
    public void testMethodReturnsNumbersBetween0And3() {
        MyRandom r = new MyRandom();
        int n1= r.Next0to3();
        int n2= r.Next0to3();
        Assert.assertTrue(n1>=0);
        Assert.assertTrue(n1<=3);
        Assert.assertTrue(n2>=0);
        Assert.assertTrue(n2<=3);
    }
    
}
