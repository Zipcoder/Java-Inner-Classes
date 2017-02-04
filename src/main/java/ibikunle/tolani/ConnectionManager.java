package ibikunle.tolani;

import java.util.ArrayList;

/**
 * Created by tolaniibikunle on 2/3/17.
 */
public class ConnectionManager {

    final int theMaximumNumberOfConnections = 4;
    ArrayList<Connection> connections = new ArrayList<Connection>();
    ManagedConnection managedConnection;

    public Connection getNewConnection(String ipAddress, String port, String protocol) {
        if (connections.size() < theMaximumNumberOfConnections) { // so if the size of the Arraylist connects is greater than the size,
            managedConnection = new ManagedConnection(ipAddress, port, protocol);
            connections.add(managedConnection); //now adding the new connection we made to  the arraylist
            return managedConnection;
        } else {
            return null;
            // returning individual connections.
        }
    }


    public Connection getNewConnection(String ipAddress, String port) {
        if (connections.size() < theMaximumNumberOfConnections) { // so if the size of the Arraylist connects is greater than the size,
            managedConnection = new ManagedConnection(ipAddress, port, "HTTP");
            connections.add(managedConnection); //now adding the new connection we made to  the arraylist
            return managedConnection;

        } else
            return null;

    }

    private class ManagedConnection implements Connection {

        String iPAddress;
        String protocol;
        String port;
        String status;

        //because ManagedConnection implements connection you now must implement all of fields and functions to ManagedConnection
        public ManagedConnection(String iPAddress, String port, String protocol) {
            this.iPAddress = iPAddress;
            this.protocol = protocol;
            this.port = port;
            this.status = "Open";
        }


        public void close() {
            this.status = "Closed";
            this.protocol = "Closed";

        }

        public String getIPAddress() {
            return this.iPAddress;
        }

        public String getProtocol() {
            return this.protocol;
        }

        public String getPort() {
            return this.port;
        }

        public String connect() {
            return "You have connected to " + this.getIPAddress() + " using " + this.getProtocol();
        }

    }
}

