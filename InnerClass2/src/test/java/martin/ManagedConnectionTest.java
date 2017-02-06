package martin;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by matthewmartin on 2/5/17.
 */
public class ManagedConnectionTest {
    ConnectionManager testManager = new ConnectionManager();
    ConnectionManager.ManagedConnection testConnection = testManager.new ManagedConnection("127.0.0.1:", 8000, "HTTP");

    @Test
    public void testGetIP(){
        String expected = "127.0.0.1:";
        String actual = testConnection.getIP();
        Assert.assertEquals("Testing the IP getter for the inner class", expected, actual);
    }

    @Test
    public void testGetPort(){
        int expected = 8000;
        int actual = testConnection.getPort();
        Assert.assertEquals("Testing the port getter in the inner class", expected, actual);
    }

    @Test
    public void testGetProtocol(){
        String expected = "HTTP";
        String actual = testConnection.getProtocol();
        Assert.assertEquals("Testing the protocol getter for the inner class", expected, actual);
    }

}


