package mattern.william;

import java.io.Closeable;
import java.util.ArrayList;

/**
 * Created by williammattern on 2/2/17.
 */
public class ConnectionManager {

    final ArrayList<Connection> connectionArrayList = new ArrayList<Connection>();
    int MAX_CONNECTIONS = 3;
    int currentNumberOfConnections = 0;


    class ManagedConnection implements mattern.william.Connection,Closeable{
        String ip;
        Protocol protocol = Protocol.HTTP;
        int port = 8000;
        Status openClosedStatus = Status.OPEN;


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
            return Protocol.ERROR;

        }

        public int getPort() {
            if(openClosedStatus == Status.OPEN){
                return this.port;
            }
            return -1;

        }

        public void close(){
            this.openClosedStatus = Status.CLOSED;
            connectionArrayList.remove(this);
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

    Connection getConnectionByIp(String ip){
        return null;
    }

    boolean checkConnectionsOverLimit() {
        return false;
    }

    void removeClosedConnection(ManagedConnection managedConnection){
    }
}