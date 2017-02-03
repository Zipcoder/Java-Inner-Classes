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
        connectionManager.buildConnection("192.168.1.1", Protocol.HTTP);
    }

    @Test
    public void connectionManagerCreationTest(){
        int actualSize = connectionManager.connectionArrayList.size(), expected = 1;
        assertEquals(expected,actualSize);
    }

    @Test
    public void connectionManagerIPReturnTest(){
        String actual = connectionManager.buildConnection("192.168.1.1", Protocol.HTTP).getIP();
        String expected = "192.168.1.1";
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerProtocolReturnTest(){
        Protocol actual = connectionManager.buildConnection("192.168.1.1", Protocol.HTTP).getProtocol();
        Protocol expected = Protocol.HTTP;
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerPortReturnTest(){
        int actual = connectionManager.buildConnection("192.168.1.1", Protocol.HTTP).getPort();
        int expected = 8000;
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerNewProtocolTest(){
        Protocol actual = connectionManager.buildConnection("192.168.1.1", Protocol.FTP).getProtocol();
        Protocol expected = Protocol.FTP;
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerNewPortReturnTest(){
        int actual = connectionManager.buildConnection("192.168.1.1", Protocol.HTTP, 8001).getPort();
        int expected = 8001;
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerMaxConnectionTest(){
        connectionManager.buildConnection("192.168.1.1", Protocol.HTTP);
        connectionManager.buildConnection("192.168.1.1", Protocol.HTTP);
        connectionManager.buildConnection("192.168.1.1", Protocol.HTTP);
        connectionManager.buildConnection("192.168.1.1", Protocol.HTTP);
        connectionManager.buildConnection("192.168.1.1", Protocol.HTTP);
        connectionManager.buildConnection("192.168.1.1", Protocol.HTTP);
        int expected = 3, actual = connectionManager.connectionArrayList.size();
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerListPortReturnTest(){
        connectionManager.buildConnection("192.168.1.1", Protocol.HTTP);
        int actual = connectionManager.connectionArrayList.get(1).getPort();
        int expected = 8000;
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerListIpReturnTest(){
        connectionManager.buildConnection("192.168.0.1", Protocol.HTTP);
        String actual = connectionManager.connectionArrayList.get(1).getIP();
        String expected = "192.168.0.1";
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerListProtocolReturnTest(){
        connectionManager.buildConnection("192.168.0.1", Protocol.HTTP);
        Protocol actual = connectionManager.connectionArrayList.get(1).getProtocol();
        Protocol expected = Protocol.HTTP;
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerListConnectTest(){
        String actual = connectionManager.connectionArrayList.get(0).connect();
        String expected = "Connected to 192.168.1.1:8000 via HTTP";
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerListConnectTestCustomPort(){
        String actual =connectionManager.buildConnection("192.168.1.1", Protocol.HTTP, 8001).connect();
        String expected = "Connected to 192.168.1.1:8001 via HTTP";
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerListConnectTestCustomIP(){
        String actual =connectionManager.buildConnection("192.168.4.4", Protocol.HTTP, 8000).connect();
        String expected = "Connected to 192.168.4.4:8000 via HTTP";
        assertEquals(expected,actual);
    }

    @Test
    public void connectionManagerListConnectTestCustomProtocol(){
        String actual =connectionManager.buildConnection("192.168.5.1", Protocol.FTP, 8001).connect();
        String expected = "Connected to 192.168.5.1:8001 via FTP";
        assertEquals(expected,actual);
    }
}
