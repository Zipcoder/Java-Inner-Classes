package mattern.william;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by williammattern on 2/2/17.
 */
public class ConnectionManager {
    List<ManagedConnection> connectionArrayList = new ArrayList<ManagedConnection>();
    int MAX_CONNECTIONS = 3;
    int currentNumberOfConnections = 0;

    class ManagedConnection implements mattern.william.Connection{
        Protocol defaultProtocol = Protocol.HTTP;
        Status defaultOpenClosedStatus = Status.OPEN;
        int defaultPort = 8000;

        String ip;  //Does not have a default value since every constructor must provide the IP.
        Protocol protocol = defaultProtocol;
        int port = defaultPort;
        Status openClosedStatus = defaultOpenClosedStatus;

        private ManagedConnection(String ip, Protocol protocol, int port){
            this.ip = ip;
            this.protocol = protocol;
            this.port = port;
        }

        private ManagedConnection(String ip, Protocol protocol){
            this.ip = ip;
            this.protocol = protocol;
        }

        private ManagedConnection(String ip, int port){
            this.ip = ip;
            this.port = port;
        }

        public String connect() {
            if(openClosedStatus == Status.OPEN) {
                StringBuilder connectOutputSB = new StringBuilder();
                connectOutputSB.append("Connected to ").append(this.getIP()).append(":").append(this.getPort()).append(" via ").append(this.getProtocol());
                String connectOutput = connectOutputSB.toString();
                return connectOutput;
            }
            return "ERROR this connection is closed";
        }

        public String getIP() {
            if(openClosedStatus == Status.OPEN){
                return this.ip;
            }
            return "ERROR this connection is closed";
        }

        public Protocol getProtocol() {
            if(openClosedStatus == Status.OPEN){
                return this.protocol;
            }
            System.out.println("ERROR this connection is closed");
            return Protocol.ERROR;
        }

        public int getPort() {
            if(openClosedStatus == Status.OPEN){
                return this.port;
            }
            System.out.println("ERROR this connection is closed");
            return -1;
        }

        public Status getStatus() {
                return this.openClosedStatus;
        }

        public void close(){
            this.openClosedStatus = Status.CLOSED;
            currentNumberOfConnections--;
        }
    }

    ManagedConnection buildConnection(String ip, Protocol protocol){
        if(currentNumberOfConnections < MAX_CONNECTIONS) {
            ManagedConnection managedConnection = new ManagedConnection(ip, protocol);
            connectionArrayList.add(managedConnection);
            currentNumberOfConnections++;
            return managedConnection;
        }
        System.out.println("MAX_CONNECTIONS already reached, unable to manage another connection");
        return null;
    }

    ManagedConnection buildConnection(String ip, int port){
        if(currentNumberOfConnections < MAX_CONNECTIONS) {
            ManagedConnection managedConnection = new ManagedConnection(ip,port);
            connectionArrayList.add(managedConnection);
            currentNumberOfConnections++;
            return managedConnection;
        }
        System.out.println("MAX_CONNECTIONS already reached, unable to manage another connection");
        return null;
    }

    ManagedConnection buildConnection(String ip, Protocol protocol, int port){
        if(currentNumberOfConnections < MAX_CONNECTIONS) {
            ManagedConnection managedConnection = new ManagedConnection(ip,protocol, port);
            connectionArrayList.add(managedConnection);
            currentNumberOfConnections++;
            return managedConnection;
        }
        System.out.println("MAX_CONNECTIONS already reached, unable to manage another connection");
        return null;
    }

    ManagedConnection getConnectionByIp(String ip){
        for (ManagedConnection connection: connectionArrayList){
            if(connection.getIP().equals(ip)){
                return connection;
            }
        }
        return null;
    }

    Connection getConnectionByProtocol(Protocol protocol){
        for (Connection connection: connectionArrayList){
            if(connection.getProtocol().equals(protocol)){
                return connection;
            }
        }
        return null;
    }
}