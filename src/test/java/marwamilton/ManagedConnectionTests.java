package marwamilton;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mkulima on 2/3/17.
 */
public class ManagedConnectionTests {

    ConnectionManager connectionManager;
    Connection managedConnection;

    @Before
    public void setUp(){
        connectionManager = new ConnectionManager(3);
        managedConnection = connectionManager.getManagedConnection("068.006.005.005",2000, Protocol.TCP);
    }

    @Test
    public void getIPTest(){
        String expectedIP = "068.006.005.005";
        String actualIP = managedConnection.getIP();
        String message = "ManagedConnection getIP mismatch";
        Assert.assertTrue(message, actualIP.equalsIgnoreCase(expectedIP));
    }

    @Test
    public void getPortTest(){
        int expectedPort = 2000;
        int actualPort= managedConnection.getPort();
        String message = "ManagedConnection getPort mismatch";
        Assert.assertEquals(message, expectedPort, actualPort);
    }

    @Test
    public void connectTest(){
        String expectedConnectMessage = "Connected to 068.006.005.005 : 2000 via  TCP. ";
        String actualConnectMessage = managedConnection.connect();
        Assert.assertEquals("Connection message mismatch", expectedConnectMessage, actualConnectMessage);
    }

    @Test
    public void toStringTest(){
        String expectedString = "068.006.005.005 : 2000,  TCP. Status:  OPEN";
        String actualString = managedConnection.toString();
        Assert.assertEquals("Connection message mismatch", expectedString, actualString);
    }

    @Test
    public void closeTest(){
        managedConnection.close();
    }



}
