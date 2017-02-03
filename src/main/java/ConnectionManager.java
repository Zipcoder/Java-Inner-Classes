import java.util.ArrayList;

/**
 * Created by randallcrame on 2/2/17.
 */
public class ConnectionManager {
    private ArrayList<ManagedConnection> connectList = new ArrayList<ManagedConnection>();
    private final int MAX_CONNECTION;
    int currentOpenConnections;
    ConnectionManager(int maxNum){
        MAX_CONNECTION = maxNum;
    }

    public ManagedConnection buildConnection(String IP, String protocol){
        if (!checkMax()){
            ManagedConnection connection = new ManagedConnection(IP, 8000, protocol);
            connectList.add(connection);
            currentOpenConnections++;
            return connection;
        } else
            return null;
    }

    public ManagedConnection buildConnection(String IP, int port, String protocol){
        if (!checkMax()){
            ManagedConnection connection = new ManagedConnection(IP, port, protocol);
            connectList.add(connection);
            currentOpenConnections++;
            return connection;
        } else
            return null;
    }

    public ManagedConnection buildConnection(String IP, int port){
        if (!checkMax()){
            ManagedConnection connection = new ManagedConnection(IP, port, "HTTP");
            connectList.add(connection);
            currentOpenConnections++;
            return connection;
        } else
            return null;
    }

    public boolean checkMax(){
        return (currentOpenConnections == MAX_CONNECTION);
    }
    private class ManagedConnection{
        String IP;
        int port;
        String protocol;
        boolean closed;

        ManagedConnection(String IP, int port, String protocol){
            this.IP = IP;
            this.port = port;
            this.protocol = protocol;
            closed = false;
        }

        protected String connect(){
            return "Connected to " + getIP() + " via " + getProtocol();
        }
        protected void close(){
            this.closed = true;
            currentOpenConnections--;
        }

        protected String getIP(){
            return (isClosed())? "Err8347" : this.IP;
        }

        protected int getPort(){
            return (isClosed())? 0000 : this.port;
        }

        protected String getProtocol(){
            return (isClosed())? "Err8323" : this.protocol;
        }

        public boolean isClosed() {
            return this.closed;
        }

    }

}
