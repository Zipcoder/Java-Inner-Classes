package reynoldstitko.gillian;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by gillianreynolds-titko on 2/2/17.
 */
public class ConnectionManager {

private int numberOfConnections = 0;
private static int numberOfOpenConnections = 0;
private final int MAX_NUMBER_OF_CONNECTIONS;

    public ArrayList<Connection> connections = new ArrayList<Connection>();

    //------Create constructor for the ConnectionManager
    ConnectionManager(){
        MAX_NUMBER_OF_CONNECTIONS = 5;
    }

    //Create a method so that we can reach into the private inner class
    public ManagedConnection createNewManagedConnection(String ip, String port){
        if(numberOfOpenConnections < MAX_NUMBER_OF_CONNECTIONS){
            ManagedConnection managedConnection = new ManagedConnection(ip, port);
            connections.add(managedConnection); //Add a new managed connection to the array
            numberOfConnections += 1;
            return managedConnection;
        } else
        return null;
    }

    public ManagedConnection createNewManagedConnection(String ip, String port, String protocol){
        if(numberOfOpenConnections < MAX_NUMBER_OF_CONNECTIONS){
            ManagedConnection managedConnection = new ManagedConnection(ip, port, protocol);
            connections.add(managedConnection); //Add a new managed connection to the array
            numberOfConnections += 1;
            return managedConnection;
        } else
            return null;
    }

    //---Create the inner class (won't test these methods since private)
    private class ManagedConnection implements Connection {
        private String protocol;
        private String port;
        private String ip;
        boolean closedState = false;
        String returnCompiledString = "";

        //Create a constructor that will take ip and a port
        ManagedConnection(String ip, String port) {
            this.ip = ip;
            this.port = port;
            protocol = "HTTP"; //Default protocol
        }

        //Create a constructor that will take an optional protocol
        ManagedConnection(String ip, String port, String protocol) {
            this(ip, port);
            this.protocol = "HTTP";
        }

        public String getIP() {

            return ip;
        }

        public String getPort() {

            return port;
        }

        public String getProtocol() {

            return protocol;
        }

        //The close() method throws an IOException
        public void close() {
            //When we close, decrease the available number of connections
            closedState = true;
            numberOfOpenConnections--;
        }

        public String connect() {
            //While we are open, show the port that is open
            if (closedState == false) {
                returnCompiledString = "Connected to " + this.getIP() + ":" + this.getPort() + " via " + this.getProtocol() + ".";
                return returnCompiledString;
            } else //Once closed, return an error message
                return "Error: connection has been closed";
        }
    }
}

