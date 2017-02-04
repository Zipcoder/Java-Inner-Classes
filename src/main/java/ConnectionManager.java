
import java.util.ArrayList;

/**
 * Created by randallcrame on 2/2/17.
 */
public class ConnectionManager {

    private ArrayList<Connection> connectList = new ArrayList<>();
    private final int MAX_CONNECTION;
    private int currentOpenConnections;

    ConnectionManager(int maxNum){
        MAX_CONNECTION = maxNum;
    }


    public Connection buildConnection (String IP, int port, String protocol){
        if (!checkMax()){
            if (port <0 || port > 65535)
                port = 0000;
            Connection connection = new ManagedConnection(IP, port, protocol);
            connectList.add(connection);
            currentOpenConnections++;
            return connection;
        } else
            return null;
    }

    public Connection getConnection(String IP, int port){
        for (Connection connection: this.connectList) {
            if (IP.equals(connection.getIP()) && port == connection.getPort())
                return connection;
        }
        return null;
    }

    public Connection getConnection(String IP, String protocol){
        for (Connection connection: this.connectList) {
            if (IP.equals(connection.getIP()) && protocol.equals(connection.getProtocol()))
                return connection;
        }
        return null;
    }

    public Connection getConnection(String IP, int port, String protocol){
        for (Connection connection: this.connectList) {
            if (IP.equals(connection.getIP()) && port == connection.getPort() && protocol.equals(connection.getProtocol()))
                return connection;
        }
        return null;
    }
    public boolean checkMax(){
        return (currentOpenConnections == MAX_CONNECTION);
    }


    private class ManagedConnection implements Connection{
        String IP;
        int port;
        String protocol;
        boolean closed;

        ManagedConnection(String IP, int port, String protocol){
            switch (protocol.toLowerCase()){
                case "ssh" :
                case "tcp/ip": this.protocol = protocol; break;
                default: this.protocol = "HTTP";
            }
            this.IP = IP;
            this.port = port;
            this.protocol = protocol;
            closed = false;
        }

        public String connect(){

            return (!isClosed())? "Connected to " + getIP()+":"+getPort() + " via " + getProtocol(): "Error message";
        }
        public  void close() {
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
            return this.protocol;
        }

        public boolean isClosed() {
            return this.closed;
        }

    }

}
