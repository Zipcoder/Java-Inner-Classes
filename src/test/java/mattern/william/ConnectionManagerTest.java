package mattern.william;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by williammattern on 2/2/17.
 */
public class ConnectionManagerTest {
    ConnectionManager connectionManager;

    @Before
    public void setUp() {
        connectionManager = new ConnectionManager();
        connectionManager.buildConnection("192.168.1.1", "HTTP");
    }

    @Test
    public void connectionManagerCreationTest(){
        int actualSize = connectionManager.connectionArrayList.size(), expected = 1;
        assertEquals(expected,actualSize);
    }

    @Test
    public void connectionManagerIPReturnTest(){
        String actual = connectionManager.buildConnection("192.168.1.1", "HTTP").getIP();
        String expected = "192.168.1.1";
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerProtocolReturnTest(){
        String actual = connectionManager.buildConnection("192.168.1.1", "HTTP").getProtocol();
        String expected = "HTTP";
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerPortReturnTest(){
        int actual = connectionManager.buildConnection("192.168.1.1", "HTTP").getPort();
        int expected = 8000;
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerMaxConnectionTest(){
        connectionManager.buildConnection("192.168.1.1", "HTTP");
        connectionManager.buildConnection("192.168.1.1", "HTTP");
        connectionManager.buildConnection("192.168.1.1", "HTTP");
        connectionManager.buildConnection("192.168.1.1", "HTTP");
        connectionManager.buildConnection("192.168.1.1", "HTTP");
        connectionManager.buildConnection("192.168.1.1", "HTTP");
        int expected = 3, actual = connectionManager.connectionArrayList.size();
        assertEquals(expected,actual);
    }

}
