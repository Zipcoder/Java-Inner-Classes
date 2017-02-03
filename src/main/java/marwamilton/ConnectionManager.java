package marwamilton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkulima on 2/3/17.
 */
public class ConnectionManager {
    int maxNumOfManagedConnections;
    List<Connection> listOfManagedConnections;

    // constructor
    ConnectionManager(int maxNumOfManagedConnections){
        this.maxNumOfManagedConnections = maxNumOfManagedConnections;
        listOfManagedConnections = new ArrayList<Connection>(maxNumOfManagedConnections);
    }

    // find connection by IP
    public String getConnection(String IPAdressToMatch){
        return findIPMatch(IPAdressToMatch);
    }

    // overload getConnection method to find connection by port #
    public String getConnection(int portToMatch){
        return findPortMatch(portToMatch);
    }

    private String findIPMatch(String IPToMatch){
        String ipMatches = "The following IP matches were found: \n";
        for (Connection connection : listOfManagedConnections)
            if(connection.getIP().equalsIgnoreCase(IPToMatch))
                ipMatches += connection.toString() + "\n";
        return ipMatches;
    }

    private String findPortMatch(int portToMatch){
        String portMatches = "The following port matches were found: \n";
        for (Connection connection : listOfManagedConnections)
            if(connection.getPort() == portToMatch)
                portMatches += connection.toString() + "\n";
        return portMatches;
    }

    // create managed connection given connection details
    public String createNewConnection(String IP, int port, Protocol protocol){
        if(notExceededMaxConnectionsAvailable()) {
            listOfManagedConnections.add(new ManagedConnection(IP, port, protocol));
            return listOfManagedConnections.get(listOfManagedConnections.size()-1).connect();
        } else
            return "Maximum Number of Connections in use";
    }

    //
    public String closeConnection(String IP, int port, Protocol protocol){
        int indexToClose = findIndexOfConnection(IP, port, protocol);
          listOfManagedConnections.get(indexToClose).close();
        return "connection closed";
    }

    // overload createConnection to create default connection when no details are provided
    public String createNewConnection(){
        if(notExceededMaxConnectionsAvailable()) {
            listOfManagedConnections.add(new ManagedConnection());
            return listOfManagedConnections.get(listOfManagedConnections.size()-1).connect();
        } else
            return "Maximum Number of Connections in use";
    }

    // list all connections
    public String listAllManagedConnections(){
        String tempStr = "";
        for(Connection connection: listOfManagedConnections)
            tempStr += connection.toString() + "\n";
        return tempStr + "\nNumber of Connections:" + listOfManagedConnections.size();
    }

    // compare list of managed connections to limit
    private boolean notExceededMaxConnectionsAvailable(){
        return listOfManagedConnections.size() < maxNumOfManagedConnections;
    }

    // accessor for inner managed class
    public ManagedConnection getManagedConnection(String IP, int port, Protocol protocol){
        return new ManagedConnection(IP,port,protocol);
    }

    private int findIndexOfConnection(String IP, int port, Protocol protocol){
        String matchString = String.format("%12s : %03d, %4s. Status: %5s", IP, port, protocol, ConnectionStatus.OPEN);
        int index = 0;
        for (int i = 0; i <listOfManagedConnections.size() ; i++) {
            if(listOfManagedConnections.get(i).toString().equals(matchString))
                index=i;
        }
        return index;
    }


    // private managed connection class
    //--------------------------------------------------------------------------------------------
    private class ManagedConnection implements Connection {
        private String IPAddress;
        private  int port;
        private Protocol protocol;
        private ConnectionStatus connectionStatus;

        // preferred constructor
        ManagedConnection(String IPAddress, int port, Protocol protocol){
            this.IPAddress = IPAddress;
            this.port = port;
            this.protocol = protocol;
            this.connectionStatus = ConnectionStatus.OPEN;
        }

        // default constructor
        ManagedConnection(){
            this.IPAddress = "001.001.001.001";
            this.port = 3007;
            this.protocol = Protocol.TCP;
            this.connectionStatus = ConnectionStatus.OPEN;
        }

        @Override
        public String getIP() {
            return this.IPAddress;
        }

        @Override
        public int getPort() {
            return this.port;
        }

        @Override
        public Protocol getProtocol() {
            return this.protocol;
        }

        @Override
        public String connect() {
            return String.format("Connected to %12s : %03d via %4s. ", IPAddress, port, protocol);
        }

        @Override
        public ConnectionStatus getConnectionStatus() {
            return connectionStatus;
        }

        @Override
        public void close(){
            connectionStatus = ConnectionStatus.CLOSED;
        }

        public String toString(){
            return String.format("%12s : %03d, %4s. Status: %5s", IPAddress, port, protocol, connectionStatus);
        }

    }
    //--------------------------------------------------------------------------------------------

}
