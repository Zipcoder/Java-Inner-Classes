package reynoldstitko.gillian;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

/**
 * Created by gillianreynolds-titko on 2/2/17.
 */
public class ConnectionManagerTest {

    ConnectionManager connectionManager;
    //ConnectionManager.ManagedConnection managedConnection = connectionManager.new ManagedConnection();

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
        ArrayList<Connection> connections = new ArrayList<>(4);
        //connections.add()
        //This method will return null if the connection limit is reached

    }

}
