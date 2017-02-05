package humphrey.jeriah.JavaInnerClasses;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by jeriahhumphrey on 2/3/17.
 */
public class ManagedConnectionTest {
    ConnectionManager c1 = new ConnectionManager();


    @Test
    public void addingConnectionIncreasesConnectionCountSize(){
        c1.buildConnection("1.0.0.0", 10);
        int expected= 1;
        int actual = c1.getOpenConnectionCount();
        assertEquals(expected, actual);
    }

    @Test
    public void buildTest(){
        c1.buildConnection("1.0.0.0", 80);
        System.out.println(c1.getList().get(0).connect());
        int expected = 1;
        int actual = c1.getList().size();
    }


    @Test
    public void buildTestTwo(){
        System.out.println( c1.getList().size());
        c1.buildConnection("192.168.0.1.", 80, "HTTP");
        System.out.println( c1.getList().size());
        int expected = 1;
        int actual = c1.getOpenConnectionCount();
        assertEquals(expected, actual);
    }

    @Test
    public void buildTestTwoUnSupportedProtocol(){
        System.out.println( c1.getList().size());
        c1.buildConnection("192.168.0.1.", 80, "SNMP");
        System.out.println( c1.getList().size());
        int expected = 0;
        int actual = c1.getOpenConnectionCount();
        assertEquals(expected, actual);
    }


    @Test
    public void connectTest()  {
        c1.buildConnection("1.0.0.1", 8000);
        String expected="Connected to 1.0.0.1:8000 via HTTP.";
        String actual = c1.getList().get(0).connect();
        assertEquals(expected, actual);

    }
    @Test
    public void getConnectionTestByIPandProtocol()  {
        c1.buildConnection("193.187.5.8", 8000, "SMTP");
        String expected="Connected to 193.187.5.8:8000 via SMTP.";
        String actual = c1.getConnectionbyIPAndProtocol("193.187.5.8", "SMTP");
        assertEquals(expected, actual);

    }

    @Test
    public void getConnectionTestByIPandProtocolNonExistantConnection()  {
        c1.buildConnection("193.187.5.8", 8000, "SMTP");
        String expected="This connection does not exist";
        String actual = c1.getConnectionbyIPAndProtocol("193.187.5.6", "SMTP");
        assertEquals(expected, actual);

    }
    @Test
    public void ipConflictTestDoesNotAddConnection1() {
        System.out.println(c1.getList().size());
        c1.buildConnection("193.187.5.8", 8000, "SMTP");
        System.out.println(c1.getList().size());
        c1.buildConnection("193.187.5.8", 8000, "FTP");
        System.out.println(c1.getList().size());
        int expected=1;
        int actual = c1.getList().size();
        assertEquals(expected, actual);

    }

    @Test
    public void ipConflictTestDoesNotAddConnection2() {
        System.out.println(c1.getList().size());
        c1.buildConnection("193.187.5.8", 8000, "SMTP");
        System.out.println(c1.getList().size());
        c1.buildConnection("193.187.5.8", 6000 );
        System.out.println(c1.getList().size());
        int expected=1;
        int actual = c1.getList().size();
        assertEquals(expected, actual);

    }

    @Test
    public void closeConnectionlowersConnectionCount() throws IOException {
        c1.buildConnection("193.187.5.8", 8000, "SMTP");
        c1.buildConnection("193.187.5.7", 6000 );
        c1.buildConnection("193.187.5.4", 6000 );
        c1.getList().get(2).close();
        int expected=2;
        int actual = c1.getOpenConnectionCount();
        assertEquals(expected, actual);

    }
    @Test
    public void closeConnectionCantShowIP() throws IOException {
        c1.buildConnection("193.187.5.8", 8000, "SMTP");
        System.out.println(c1.getList().get(0).getIp());
        c1.getList().get(0).close();
        System.out.println(c1.getList().get(0).getIp());
        String expected="invalid";
        String actual = c1.getList().get(0).getIp();
        assertEquals(expected, actual);

    }
    @Test
    public void closeConnectionCantShowPort() throws IOException {
        c1.buildConnection("193.187.5.8", 8000, "SMTP");
        System.out.println(c1.getList().get(0).getPort());
        c1.getList().get(0).close();
        System.out.println(c1.getList().get(0).getPort());
        int expected=0;
        int actual = c1.getList().get(0).getPort();
        assertEquals(expected, actual);
    }

    @Test
    public void closeConnectionCantShowProtocol() throws IOException {
        c1.buildConnection("193.187.5.8", 8000, "SMTP");
        System.out.println(c1.getList().get(0).getProtocol() );
        c1.getList().get(0).close();
        System.out.println(c1.getList().get(0).getProtocol());
        String expected="invalid";
        String actual = c1.getList().get(0).getProtocol();
        assertEquals(expected, actual);

    }
    @Test
    public void closeConnectionCantShowzFullConnection() throws IOException {
        c1.buildConnection("193.187.5.8", 8000, "SMTP");
        c1.getList().get(0).close();
        String expected="error: connection closed";
        String actual = c1.getList().get(0).connect();
        assertEquals(expected, actual);

    }






    @Test
    public void checkMaxTest(){
        c1.buildConnection("1.0.0.1", 8000);
        assertFalse("I expect it to be false", c1.checkMax());
    }

    @Test
    public void checkMaxTest2(){
        c1.buildConnection("1.0.0.1", 8000);
        c1.buildConnection("1.0.0.2", 8000);
        c1.buildConnection("1.0.0.3", 8000);
        assertTrue("I expect it to be true", c1.checkMax());
    }

    @Test
    public void ConnectionCantHoldMoreThan3OpenConnections(){
        c1.buildConnection("1.0.0.1", 8000);
        System.out.println(c1.getOpenConnectionCount());
        c1.buildConnection("1.0.0.2", 8000);
        System.out.println(c1.getOpenConnectionCount());
        c1.buildConnection("1.0.0.3", 8000);
        System.out.println(c1.getOpenConnectionCount());
        c1.buildConnection("1.0.0.4", 8000);
        System.out.println(c1.getOpenConnectionCount());
        int expected=3;
        int actual = c1.getOpenConnectionCount();
        assertEquals(expected, actual);
    }


}