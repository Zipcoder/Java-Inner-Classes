package reynoldstitko.gillian;

import java.util.ArrayList;

/**
 * Created by gillianreynolds-titko on 2/2/17.
 */
public class ConnectionManager {

private int numberOfConnections = 0;
private int numberOfConnectionsInitialized = 0;
private int MAX_NUMBER_OF_CONNECTIONS;

    public ArrayList<Connection> connections = new ArrayList<Connection>();

    //------Create constructor for the ConnectionManager
    ConnectionManager(){
        MAX_NUMBER_OF_CONNECTIONS = 5;
    }

    //--Testable methods
    public Connection getConnectionWithIPProtocol(){
        return null;
    }

    public Connection getConnectionWithIPPort(){
        //set protocol = "HTTP" as a default here
        return null;
    }

    public String maxNumberOfConnectionsReached(){
        //This method will return null if the connection limit is reached
        return null;
    }


    //---Create the inner class (can't test these methods since private)
    private class ManagedConnection implements Connection {
        private String protocol;
        private int port;
        private String ip;

        //Create a constructor that will take ip and a port
        ManagedConnection(String ip, int port){
            this.ip = ip;
            this.port = port;
        }

        //Create a constructor that will take an optional protocol
        ManagedConnection(String ip, int port, String protocol){
            this(ip, port);
            this.protocol = "HTTP";
        }

        public String getIP(){
            return null;
        }

        public int getPort(){
            return 0;
        }

        public String getProtocol(){
           return null;
        }

        public void close(){

        }

        public String connect(){
         return null;
        }

    }
}
