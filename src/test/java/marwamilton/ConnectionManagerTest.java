package marwamilton;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mkulima on 2/3/17.
 */
public class ConnectionManagerTest {

    ConnectionManager connectionManager;

    @Before
    public void setup(){
        connectionManager  = new ConnectionManager(3);
        connectionManager.createNewConnection("261.001.001.001",2000, Protocol.HTTP);
        connectionManager.createNewConnection("200.001.001.001",8009, Protocol.HTTP);
    }

    @Test
    public void getConnectionByIPTest(){
        String expectedIPMessage = "The following IP matches were found: \n" +
                "261.001.001.001 : 2000, HTTP.\n";
        String actualGetConnectionMessage = connectionManager.getConnection("261.001.001.001");
        Assert.assertEquals("IP getConnection message mismatch!", expectedIPMessage,actualGetConnectionMessage);
    }

    @Test
    public void getConnectionByPortTest(){
        String expectedIPMessage = "The following port matches were found: \n" +
                "200.001.001.001 : 8009, HTTP.\n";
        String actualGetConnectionMessage = connectionManager.getConnection(8009);
        Assert.assertEquals("Port getConnection message mismatch!", expectedIPMessage,actualGetConnectionMessage);
    }

    @Test
    public void listAllManagedConnectionsTest(){
        String expectedList = "261.001.001.001 : 2000, HTTP.\n" +
        "200.001.001.001 : 8009, HTTP.\n\n" +
                "Number of Connections:2";
        String actualList = connectionManager.listAllManagedConnections();
        Assert.assertEquals("List mismatch!", expectedList,actualList);
    }

    @Test
    public void createNewConnectionTest(){
        String expectedMessage = "Connected to 261.006.005.005 : 2000 via  TCP. ";
        String actualMessage = connectionManager.createNewConnection("261.006.005.005",2000, Protocol.TCP);
        Assert.assertEquals("New connection message mismatch",expectedMessage,actualMessage);
    }

    @Test
    public void createNewConnectionDefault(){
        String expectedMessage = "Connected to 001.001.001.001 : 3007 via  TCP. ";
        String actualMessage = connectionManager.createNewConnection();
        Assert.assertEquals("New default connection message mismatch",expectedMessage,actualMessage);
    }

    @Test
    public void closeConnectionTest(){
        connectionManager.closeConnection("200.001.001.001",8009, Protocol.HTTP);
        ConnectionStatus expectedStatus = ConnectionStatus.CLOSED;
        ConnectionStatus actualStatus = connectionManager.listOfManagedConnections.get(1).getConnectionStatus();
        Assert.assertEquals("Connection status mismatch!", expectedStatus, actualStatus);
    }

}
