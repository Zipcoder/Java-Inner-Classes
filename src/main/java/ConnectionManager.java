import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by randallcrame on 2/2/17.
 */
public class ConnectionManager {
    public ArrayList<Connection> getConnectList() {
        return connectList;
    }

    private ArrayList<Connection> connectList = new ArrayList<Connection>();
    private final int MAX_CONNECTION;
    int currentOpenConnections;
    ConnectionManager(int maxNum){
        MAX_CONNECTION = maxNum;
    }

    public Connection buildConnection(String IP, String protocol){
        if (!checkMax()){
            Connection connection = new ManagedConnection(IP, 8000, protocol);
            connectList.add(connection);
            currentOpenConnections++;
            return connection;
        } else
            return null;
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

    public Connection buildConnection(String IP, int port){
        if (!checkMax()){
            if (port <0 || port > 65535)
                port = 0000;
            Connection connection = new ManagedConnection(IP, port, "HTTP");
            connectList.add(connection);
            currentOpenConnections++;
            return connection;
        } else
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
            return (isClosed())? "Err8323" : this.protocol;
        }

        public boolean isClosed() {
            return this.closed;
        }

    }

}
