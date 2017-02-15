package PracticingTDD;

import ConnectionManagement.ConnectionManager;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by alfatihmukhtar on 2/3/17.
 */
public class ManagedConnectionTest {
    ConnectionManager manager = new ConnectionManager();
    ConnectionManager.ManagedConnection connection = manager.new ManagedConnection();

    @Test
    public void TestGetIP() {
        String expected = "beep beep";
        String actual = connection.getIPAddress();
        assertEquals("Wrong",expected,actual);
    }

    @Test
    public void TestConnectMethod() {
        String expected = "This is a long and boring string";
        String actual = connection.connect();
        assertEquals("Your implementation of the connect method does not work.",expected,actual);
    }

    @Test
    public void TestCloseMethod() {
        connection.close();
        String expected = "This is a long and boring string";
        String actual = connection.connect();
        assertEquals("Your implementation of the connect method does not work.",expected,actual);
    }
}
