package ibikunle.tolani;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by tolaniibikunle on 2/3/17.
 */
public class ConnectionTests {

    String protocol = "HTTP";
    String port = " 7000";
    String iPAddress = "12.8.13.80";

    ArrayList<Connection> connections = new ArrayList<Connection>();
    Connection connection = new ConnectionManager().getNewConnection("127.0.0.1", "8000", "HTTP");


    @Test
    public void getNewConnectionTwoParametersTest() {
        Connection connection = new ConnectionManager().getNewConnection(iPAddress, port);
        connections.add(connection);
        int expected = 1;
        int actual = connections.size(); // now we are checking to see if the array size has increased to one
        Assert.assertEquals("I am expecting 1", expected, actual);
    }

    @Test
    public void getNewConnectionThreeParametersTest() {
        Connection connection = new ConnectionManager().getNewConnection(iPAddress, port, protocol);
        connections.add(connection);
        int expected = 1;
        int actual = connections.size();
        assertEquals("I am expecting 0", expected, actual);
    }

    @Test
    public void getIP() {
        Connection connection = new ConnectionManager().getNewConnection("127.0.0.1", "8000", "HTTP");
        String expected = "127.0.0.1";
        String actual = connection.getIPAddress();
        Assert.assertEquals("I am expecting to get the IPaddress using getIP", expected, actual);


    }

    @Test
    public void getPort() {
        String expected = "8000";
        String actual = connection.getPort();
        Assert.assertEquals("I am expecting to get the port data using getPort ", expected, actual);
    }

    @Test
    public void getProtocol() {
        String expected = "HTTP";
        String actual = connection.getProtocol();
        Assert.assertEquals("I am expecting to get the protocol data using getProtocol", expected, actual);

    }

    @Test
    public void close() {

    }

    @Test
    public void connectTest() {
        String expected = "You have connected to 127.0.0.1 using 8000";
        String actual = connection.connect();
        assertEquals("I expected this to pass", expected, actual);


    }
}


