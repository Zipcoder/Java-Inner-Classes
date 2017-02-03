package mattern.william;

import java.util.ArrayList;

/**
 * Created by williammattern on 2/2/17.
 */
public class ConnectionManager {

    ArrayList<ManagedConnection> connectionArrayList;
    int MAX_CONNECTIONS = 3;

    private class ManagedConnection implements mattern.william.Connection{
        String ip;
        String protocol = "HTTP";
        int port = 8000;

        ManagedConnection(String ip, String protocol, int port){
            this.ip = ip;
            this.protocol = protocol;
            this.port = port;
        }

        ManagedConnection(String ip, String protocol){
            this.ip = ip;
            this.protocol = protocol;
        }

        ManagedConnection(String ip, int port){
            this.ip = ip;
            this.port = port;
        }

        public String connect() {
            return null;
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

    void buildConnection(String ip, String protocol){
        ManagedConnection m = new ManagedConnection(ip,protocol);
        connectionArrayList.add(m);
    }

    void buildConnection(String ip, int port){
        connectionArrayList.add(new ManagedConnection(ip,port));
    }

    void buildConnection(String ip, String protocol, int port){
        connectionArrayList.add(new ManagedConnection(ip,protocol,port));
    }

    ManagedConnection getConnectionByIp(int ip){
        return this.connectionArrayList.get(ip);
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