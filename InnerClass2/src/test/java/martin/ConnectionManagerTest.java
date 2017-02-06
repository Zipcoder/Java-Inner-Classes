package martin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by matthewmartin on 2/5/17.
 */
public class ConnectionManagerTest {
    ConnectionManager testManager2;

    @Before
    public void setup(){
        testManager2 = new ConnectionManager();
    }

    @Test
    public void testBuildConnection() {
        testManager2.buildConnection("127.0.0.1:", 8000, "HTTP");
        int expected = 1;
        int actual = testManager2.connectionsArray.size();
        Assert.assertEquals("Im expecting the build connection to add one connection to the array", expected, actual);
    }

    @Test
    public void maxNumberOfConnectionsTest() {
        boolean expected = false;
        boolean actual = testManager2.maxNumberOfConnections(3);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void connectTest(){
        String expected = ("Connected to 127.0.0.1:8000 via HTTP");
        Connection actual = testManager2.getConnection("127.0.0.1:", 8000, "HTTP" );
        Assert.assertEquals(expected, actual);
    }

}
