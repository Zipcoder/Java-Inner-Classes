package armstrong.alexandra;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by alexandraarmstrong on 2/2/17.
 */
public class ConnectionManagerTest {
    ConnectionManager connger;

    @Before
    public void setUp(){
        connger = new ConnectionManager();
    }

    @Test
    public void getConnectionTest1() {
        connger.getConnection("Bling", "Bing");
        int expected = 1;
        int actual = connger.connections.size();
        assertEquals(actual, expected);
    }

    @Test
    public void getConnectionTest2() {
        connger.getConnection("Bling", "Bing", "Boop");
        int expected = 1;
        int actual = connger.connections.size();
        assertEquals(actual, expected);
    }
}