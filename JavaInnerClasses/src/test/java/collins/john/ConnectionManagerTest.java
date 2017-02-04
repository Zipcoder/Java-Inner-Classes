package collins.john;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by johncollins on 2/3/17.
 */
public class ConnectionManagerTest {
    ConnectionManager testManager;
    ConnectionManager.ManagedConnection testConnection;

    @Before
    public void setUp() {
        testManager = new ConnectionManager(5);
        testConnection = testManager.new ManagedConnection("192.168.92.102", 8081);
        testManager.connectionsList.add(testConnection);

    }

    @Test
    public void makeConnectionTest() {
        int actual = testManager.connectionsList.size();
        //int expected = 1; //real test
        int expected = -1; //fail test
        assertEquals("should return 1 connection in connectionsList", expected, actual);
    }

    @Test
    public void getConnectionByIPxProtocolxPortTest() {
        String actual = testManager.getConnectionsByIPxPortxProtocol("192.168.92.102", 8081).toString();
        //String expected = "Found existing connection 192.168.92.102:8081 HTTP";
        String expected = null;
        assertEquals("should return...", expected, actual);

    }

    @Test
    public void connectTest() {
        String actual = testConnection.connect();
        //String expected = "Connected to 192.168.92.102:8081 via HTTP.";
        String expected = null;
        assertEquals("should return...", expected, actual);
    }

    @Test
    public void closeTest() {
        testConnection.close();
        String actual = testManager.getConnectionsByIPxPortxProtocol("192.168.92.102", 8081).toString();
        //String expected = "Found existing connection xxx.xxx.xxx.xxx:-1 none";
        String expected = "fail";
        assertEquals("should return...", expected, actual);
    }
}
