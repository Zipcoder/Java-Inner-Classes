package martin;

import java.util.ArrayList;

/**
 * Created by matthewmartin on 2/5/17.
 */
public class ConnectionManager {
    public ArrayList<Connection> connectionsArray = new ArrayList<>();
    int MaxConnection = 3;


    public void buildConnection(String IP, int port, String protocol){
        ManagedConnection mc = new ManagedConnection(IP, port, protocol);
        connectionsArray.add(mc);
    }

    public String getConnection(String IP, String protocol) {
        if (maxNumberOfConnections(connectionsArray.size())) {
        }
        return null;
    }

    public ManagedConnection getConnection(String IP, int port, String protocol) {
        if (maxNumberOfConnections(connectionsArray.size())) {
            return null;
        } else
            return new ConnectionManager.ManagedConnection(IP, port, protocol);
    }


    public boolean maxNumberOfConnections(int numberOfConnections) {
        boolean max = false;
        if (numberOfConnections > 3) {
            max = true;
        }
        return max;
    }


    class ManagedConnection implements Connection{
        String IP;
        String protocol;
        int port;
        boolean closed;

        @Override
        public void close(){
        }

        public String getProtocol(){

            if(protocol == null){
                return "HTTP";
            }else
                return this.protocol;
        }

        public int getPort(){
            return this.port;
        }

        public String getIP(){
            return this.IP;
        }

        public ManagedConnection(String ipAddress, int portNumber, String protocolAddress){
            IP = ipAddress;
            port = portNumber;
            protocol = protocolAddress;
        }

        @Override
        public String connect() {
            return ("Connected to :" + getIP() + getPort() + " via " + getProtocol());
        }

        public String toString() {
            return ("Connected to :" + getIP() + getPort() + " via " + getProtocol());

        }

        public String checkIfOpen() {
            if (maxNumberOfConnections(connectionsArray.size())) {
            }
            return "Closed";
        }
    }

}
