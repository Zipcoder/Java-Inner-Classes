import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by randallcrame on 2/2/17.
 */
public class ConnectionManagerTest {
    ConnectionManager manager;
    @Before
    public void setUp(){
        manager = new ConnectionManager(3);
    }

    /*@Test
    public void connectTest() {
        String expected = "Connected to 127.0.0.1:8000 via HTTP";
        String actual = managedConnection.connect();
        Assert.assertEquals("Expected return of String 'Connected to 127.0.0.1:8000 via HTTP' when called.", expected, actual);
    }

    @Test
    public void connectErrorTest() {
        String expected = "Error message";
        String actual = managedConnection.connect();
        Assert.assertEquals("Expected return of String 'Connected to 127.0.0.1:8000 via HTTP' when called.", expected, actual);
    }

    @Test
    public void closeIsClosedTest(){
        managedConnection.close();
        boolean expected = true;
        boolean actual = managedConnection.isClosed();
        Assert.assertEquals("Expected to return true because .close() was called", expected, actual);

    }

    @Test
    public void closeIsOpenTest(){
        boolean expected = false;
        boolean actual = managedConnection.isClosed();
        Assert.assertEquals("Expected to return false because closed is false", expected, actual);
    }/**/

    @Test
    public void buildConnectionStringStringCallIPTest(){
        String expected = "127.1.1.1";
        String actual = manager.buildConnection("127.1.1.1", "HTTP").getIP();
        Assert.assertEquals("expect to return '127.1.1.1 '", expected, actual);
    }

    @Test
    public void buildConnectionStringStringCallProtocolTest(){
        String expected = "HTTP";
        String actual = manager.buildConnection("127.1.1.1", "HTTP").getProtocol();
        Assert.assertEquals("expect to return '127.1.1.1 '", expected, actual);
    }

    @Test
    public void buildConnectionStringIntStringCallIPTest(){
        String expected = "127.1.1.1";
        String actual = manager.buildConnection("127.1.1.1", 8000,"HTTP").getIP();
        Assert.assertEquals("expect to return '127.1.1.1 '", expected, actual);
    }

    @Test
    public void buildConnectionStringIntStringCallPortTest(){
        int expected = 8000;
        int actual = manager.buildConnection("127.1.1.1", 8000,"HTTP").getPort();
        Assert.assertEquals("expect to return '127.1.1.1 '", expected, actual);
    }
    @Test
    public void buildConnectionStringIntStringCallProtocolTest(){
        String expected = "HTTP";
        String actual = manager.buildConnection("127.1.1.1", 8000,"HTTP").getProtocol();
        Assert.assertEquals("expect to return '127.1.1.1 '", expected, actual);
    }

    @Test
    public void buildConnectionStringIntCallPortTest(){
        int expected = 8000;
        int actual = manager.buildConnection("127.1.1.1", 8000).getPort();
        Assert.assertEquals("expect to return 8000", expected, actual);
    }

    @Test
    public void buildConnectionStringInCallIPTest(){
        String expected = "127.1.1.1";
        String actual = manager.buildConnection("127.1.1.1", 8000).getIP();
        Assert.assertEquals("expect to return 8000", expected, actual);
    }

    @Test
    public void checkMaxTest(){
        boolean actual = manager.checkMax();
        Assert.assertFalse("expected false to return due to not reaching max check", actual);
    }

    @Test
    public void checkMaxfullTest(){
        boolean actual = manager.checkMax();
        Assert.assertFalse("expected true to return due to  reaching max check", actual);
    }

    @Test
    public void checkMax1ClosedTest(){
        boolean actual = manager.checkMax();
        Assert.assertFalse("expected false to return due to having one closed", actual);
    }
}
