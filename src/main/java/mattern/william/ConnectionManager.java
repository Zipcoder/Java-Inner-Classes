package mattern.william;

import java.util.ArrayList;

/**
 * Created by williammattern on 2/2/17.
 */
public class ConnectionManager {

    ArrayList<ManagedConnection> connectionArrayList = new ArrayList<ManagedConnection>();
    int MAX_CONNECTIONS = 3;
    int currentNumberOfConnections = 0;

    class ManagedConnection implements mattern.william.Connection{
        String ip;
        String protocol = "HTTP";
        int port = 8000;

        private ManagedConnection(String ip, String protocol, int port){
            this.ip = ip;
            this.protocol = protocol;
            this.port = port;
        }

        private ManagedConnection(String ip, String protocol){
            this.ip = ip;
            this.protocol = protocol;
        }

        private ManagedConnection(String ip, int port){
            this.ip = ip;
            this.port = port;
        }

        public String connect() {
            StringBuilder connectOutputSB = new StringBuilder();
            connectOutputSB.append("Connected to ").append(this.getIP()).append(":").append(this.getPort()).append(" via ").append(this.getProtocol());
            String connectOutput = connectOutputSB.toString();
            return connectOutput;
        }

        public String getIP() {
            return this.ip;
        }

        public String getProtocol() {
            return this.protocol;
        }

        public int getPort() {
            return this.port;
        }

        void close(){

        }
    }

    ManagedConnection buildConnection(String ip, String protocol){
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

    ManagedConnection buildConnection(String ip, String protocol, int port){
        if(currentNumberOfConnections < MAX_CONNECTIONS) {
            ManagedConnection managedConnection = new ManagedConnection(ip,port);
            connectionArrayList.add(managedConnection);
            currentNumberOfConnections++;
            return managedConnection;
        }
        System.out.println("MAX_CONNECTIONS already reached, unable to manage another connection");
        return null;
    }

    ManagedConnection getConnectionByIp(String ip){
        return null;
    }

    boolean checkConnectionsOverLimit() {
        return false;
    }

    void removeClosedConnection(ManagedConnection managedConnection){
    }

    public String connect() {
        return null;
    }

    public String getIP() {
        return null;
    }

    public String getProtocol() {
        return null;
    }

    public int getPort() {
        return 0;
    }
}