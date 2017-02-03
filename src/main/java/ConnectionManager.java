import java.util.ArrayList;

/**
 * Created by randallcrame on 2/2/17.
 */
public class ConnectionManager {
    private ArrayList<ManagedConnection> connectList = new ArrayList<ManagedConnection>();
    ManagedConnection testConnection = new ManagedConnection("1231.21.2.4",123, "HTTP");
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
            if (port <0 || port > 65535)
                port = 0000;
            ManagedConnection connection = new ManagedConnection(IP, port, protocol);
            connectList.add(connection);
            currentOpenConnections++;
            return connection;
        } else
            return null;
    }

    public ManagedConnection buildConnection(String IP, int port){
        if (!checkMax()){
            if (port <0 || port > 65535)
                port = 0000;
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

    /*public String getInnerIP(ArrayList<ManagedConnection> connectionList, int number){

        return connectionList.get(number).getIP();
    }/**/
    public class ManagedConnection{
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

            return (!isClosed())? "Connected to " + getIP()+":"+getPort() + " via " + getProtocol(): "Error message";
        }
        protected void close(){
            this.closed = true;
            currentOpenConnections--;
        }

        public String getIP(){
            return (isClosed())? "Err8347" : this.IP;
        }

        public int getPort(){
            return (isClosed())? 0000 : this.port;
        }

        public String getProtocol(){
            return (isClosed())? "Err8323" : this.protocol;
        }

        public boolean isClosed() {
            return this.closed;
        }

    }

}
