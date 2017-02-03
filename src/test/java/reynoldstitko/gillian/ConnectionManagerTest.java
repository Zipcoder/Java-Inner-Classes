package reynoldstitko.gillian;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by gillianreynolds-titko on 2/2/17.
 */
public class ConnectionManagerTest {

    ConnectionManager connectionManager;

    @Before
    public void setUp(){
        connectionManager = new ConnectionManager();
    }

    //--Testable methods
    @Test
    public void getConnectionWithIPProtocol(){

    }

    @Test
    public void getConnectionWithIPPort(){
        //set protocol = "HTTP" as a default here
    }


    @Test
    public void maxNumberOfConnectionsReached(){
        //This method wil return null if the connection limit is reached
    }
    //------
}
