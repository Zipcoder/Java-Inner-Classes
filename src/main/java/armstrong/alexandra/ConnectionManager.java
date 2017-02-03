package armstrong.alexandra;

import java.util.ArrayList;

/**
 * Created by alexandraarmstrong on 2/2/17.
 */
public class ConnectionManager {
    private final int ALLOWED_CONNECTIONS = 3;
    public static int connectionCounter = 0;
    private boolean open = true;
    public ArrayList<ManagedConnection> connections = new ArrayList<ManagedConnection>();

    public void getConnection(String IP, String port){
        ManagedConnection conn = new ManagedConnection(IP, port);
        if (connectionCounter == ALLOWED_CONNECTIONS){
            System.out.println("No more connections permitted.");
        } else {
            connections.add(conn);
        }
    }

    public void getConnection(String IP, String port, String protocol){
        ManagedConnection conn = new ManagedConnection(IP, port, protocol);
        if (connectionCounter == ALLOWED_CONNECTIONS){
            System.out.println("No more connections permitted.");
        } else {
            connections.add(conn);
        }
    }

    private class ManagedConnection implements Connection {
        private String port;
        private String iPAddress;
        private String protocol;

        public ManagedConnection(String IP, String port){
            this.iPAddress = IP;
            this.port = port;
        }

        public ManagedConnection(String IP, String port, String protocol){
            this(IP, port);
            this.protocol = protocol;
        }

        public void close(){
            if(open){
                open = false;
            }
        }

        public String connect(){
            if(open){
                return "You are connected";
            } else {
                return "This connection is closed";
            }
        }

        public String getIP(){
            return iPAddress;
        }

        public String getPort(){
            return port;
        }
        public String getProtocol(){
            return protocol;
        }
    }
}
