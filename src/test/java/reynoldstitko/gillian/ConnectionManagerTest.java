package reynoldstitko.gillian;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by gillianreynolds-titko on 2/2/17.
 */
public class ConnectionManagerTest {

    ConnectionManager connectionManager;

    @Before
    public void setUp(){

        connectionManager = new ConnectionManager();
    }

    /* Tests to include:
        Getting connections from the ConnectionManager
        Requesting too many connections
        Getting field values from a requested connection
        Getting (invalid) field values from a closed connection
    */

    //Test if we have less than the maximum number of connections
    @Test
    public void maxNumberOfConnectionsNotReachedTest(){
        connectionManager.createNewManagedConnection("128.0.1", "8000", "HTTP"); //Create a managed connection
        connectionManager.createNewManagedConnection("128.0.2", "4000", "HTTP");
        connectionManager.createNewManagedConnection("128.1.3", "4001", "HTTP");

        int actual =  connectionManager.connections.size();
        int expected = 3;
        assertEquals("Expected 3", expected, actual);
    }

    //Test if we have more than the maximum number of connections
    @Test
    public void maxNumberOfConnectionsReachedTest(){
        connectionManager.createNewManagedConnection("128.0.1", "8000", "HTTP"); //Create a managed connection
        connectionManager.createNewManagedConnection("128.0.2", "4000", "HTTP");
        connectionManager.createNewManagedConnection("128.1.3", "4001", "HTTP");
        connectionManager.createNewManagedConnection("128.0.1", "8000", "HTTP"); //Create a managed connection
        connectionManager.createNewManagedConnection("128.0.2", "4000", "HTTP");
        Connection actual =  connectionManager.createNewManagedConnection("128.1.3", "4001", "HTTP");
        Connection expected = null;
        assertEquals("Expected null", expected, actual);
        //This method will return null if the connection limit is reached
    }

    //Test getting connections from the Manager
    @Test
    public void managedConnectionTest(){
        connectionManager.createNewManagedConnection("128.0.1", "8000", "HTTP");
        assertTrue(connectionManager.connections.contains(connectionManager.createNewManagedConnection("128.8.2", "4000", "HTTP")));
    }

    //Check getting IP, Protocol and Port information
    @Test
    public void getIPTest(){
        Connection connection = connectionManager.createNewManagedConnection("128.0.1", "8000", "HTTP");
        String expected = "128.0.1";
        String actual = connection.getIP();
        assertEquals("Expected 128.0.1", expected, actual);
    }

    @Test
    public void getProtocolTest(){
        Connection connection = connectionManager.createNewManagedConnection("128.0.1", "4000", "HTTP");
        String expected = "HTTP";
        String actual = connection.getProtocol();
        assertEquals("Expected 4000", expected, actual);
    }

    @Test
    public void getPortTest(){
        Connection connection = connectionManager.createNewManagedConnection("128.0.1", "8000", "HTTP");
        String expected = "8000";
        String actual = connection.getPort();
        assertEquals("Expected 8000", expected, actual);
    }

    //Test if the connect() function returns the correct string value
    @Test
    public void getConnectTest(){
    Connection connection = connectionManager.createNewManagedConnection("128.0.1", "8000", "HTTP");
    String expected = "Connected to 128.0.1:8000 via HTTP.";
    String actual = connection.connect();
    assertEquals("Expected string", expected, actual);
    }
}
