package battin.preston;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by prestonbattin on 2/2/17.
 */
public class ConnectionManagerTest {

    Connection connection;
    ConnectionManager manager = new ConnectionManager();


    @Before public void setup(){

        for(int i = 0; i < manager.getCONNECTION_LIMIT(); i++) {
            connection = manager.getManagedConnection("192.168.1.1", "8001", Protocol.Proto.HTTP);
        }
}

    @Test
    public void ManagedConnectionConnectTest(){

        String expected = "Connected to 192.168.1.1:8001 via HTTP";
        String actual = connection.connect();
        Assert.assertEquals("Testing the connect has the correct output", expected, actual);
    }

    @Test
    public void addToConnectionListTest(){

        int expected = 10;
        int actual = manager.connections.size();
        Assert.assertEquals("Testing that a Managed Connection Was added to list", expected, actual);

    }

    @Test
    public void tooManyConnectionsTest(){

        connection = manager.getManagedConnection("192.168.1.1", "8001", Protocol.Proto.HTTP);
        int expected = 10;
        int actual = manager.connections.size();
        Assert.assertEquals("Making sure no more connections are made", expected , actual);

    }

    @Test
    public void getConnectionTest(){

        Connection expected = manager.connections.get(0);
        Connection actual = manager.getConnection("192.168.1.1", Protocol.Proto.HTTP);
        Assert.assertEquals("Testing the get connection method", expected, actual);

    }

    @Test
    public void closeIPTest(){

        manager.connections.get(0).close();
        String expected = "Error CLOSED";
        String actual = manager.connections.get(0).getIP();
        Assert.assertEquals("Testing a closed connection IP", expected, actual);
    }

    @Test
    public void overwriteClosedConnectionTest(){

        manager.connections.get(5).close();
        connection = manager.getManagedConnection("192.168.1.1", "8003", Protocol.Proto.FTP);
        String expected = "8003";
        String actual = manager.connections.get(5).getPort();
        Assert.assertEquals("Testing that it over wrote a closed port when connections are full",
                expected, actual);

    }

}
