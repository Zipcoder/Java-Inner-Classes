package PracticingTDD;

import ConnectionManagement.ConnectionManager;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by alfatihmukhtar on 2/3/17.
 */
public class ConnectionManagerTest {
    ConnectionManager testManager = new ConnectionManager(2);

    @Before
    public void setUp() {
        testManager.makeConnection("HTTP");
    }

    @Test
    public void TestHasRoomForConnections() {
        boolean expected = true;
        boolean actual = testManager.hasRoomForConnections();
        assertEquals("This does not prove that you have room for connections",expected,actual);
    }

    @Test
    public void TestGetActiveConnections() {
        int expected = 1;
        int actual = testManager.getActiveConnections().size();
        assertEquals("You are not returning the correct size for the ArrayList",actual,expected);
    }

    @Test
    public void TestIsClosed() {
        testManager.closeConnection(0);
        int expected = 0;
        int actual = testManager.getActiveConnections().size();
        assertEquals("The connection is not closed properly",expected,actual);
    }
}
