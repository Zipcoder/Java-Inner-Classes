import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by randallcrame on 2/2/17.
 */
public class ConnectionManagerTest {
    ConnectionManager manager;
    Connection connection;
    @Before
    public void setUp(){
        manager = new ConnectionManager(3);
        connection = manager.buildConnection("127.0.0.1", 8000, "HTTP");
    }

    @Test
    public void connectTest() {
        String expected = "Connected to 127.0.0.1:8000 via HTTP";
        String actual = connection.connect();
        Assert.assertEquals("Expected return of String 'Connected to 127.0.0.1:8000 via HTTP' when called.", expected, actual);
    }

    @Test
    public void connectErrorTest() {
        try{
            connection.close();
        }
        catch (IOException e){}
        String expected = "Error message";
        String actual = connection.connect();
        Assert.assertEquals("Expected return of String 'Error message' when called.", expected, actual);
    }

    @Test
    public void connectIPErrorTest() {
        try{
            connection.close();
        }
        catch (IOException e){}
        String expected = "Err8347";
        String actual = connection.getIP();
        Assert.assertEquals("Expected return of String 'Err8347' when called.", expected, actual);
    }

    @Test
    public void connectPortErrorTest() {
        try{
            connection.close();
        }
        catch (IOException e){}
        int expected = 0;
        int actual = connection.getPort();
        Assert.assertEquals("Expected return of 0 when called.", expected, actual);
    }
    @Test
    public void closeIsClosedTest(){
        try{
            connection.close();
        }
        catch (IOException e){}
        boolean expected = true;
        boolean actual = connection.isClosed();
        Assert.assertEquals("Expected to return true because .close() was called", expected, actual);

    }

    @Test
    public void closeIsOpenTest(){
        boolean expected = false;
        boolean actual = connection.isClosed();
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
    public void buildConnectionStringIntNegativePortCallPortTest(){
        int expected = 0000;
        int actual = manager.buildConnection("127.1.1.1", -32452).getPort();
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
    public void checkMaxFullTest(){
        manager.buildConnection("IP",234);
        manager.buildConnection("IP2", 324234);
        boolean actual = manager.checkMax();
        Assert.assertTrue("expected true to return due to  reaching max check", actual);
    }

    @Test
    public void checkMax1ClosedTest(){
        manager.buildConnection("IP",234);
        try{
            connection.close();
        }
        catch (IOException e){}
        manager.buildConnection("IP2", 324234);

        boolean actual = manager.checkMax();
        Assert.assertFalse("expected false to return due to having one closed", actual);
    }

    @Test
    public void buildConnectionReturnNullTest(){
        manager.buildConnection("IP",234);
        manager.buildConnection("IP2", 324234);
        Assert.assertNull(manager.buildConnection("IP3", 34234));
    }


}
