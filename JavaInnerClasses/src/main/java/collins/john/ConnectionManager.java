package collins.john;

import java.util.ArrayList;

/**
 * Created by johncollins on 2/3/17.
 */
public class ConnectionManager {
    private int limit;
    private int connectionsCount;
    private boolean isLimit;
    protected ArrayList<ManagedConnection> connectionsList;

    ConnectionManager(int limit) {
        this.limit = limit;
        connectionsCount = 0;
        isLimit = false;
        connectionsList = new ArrayList<ManagedConnection>();
    }

    public Connection makeConnection(String IP, int port) {
        if (connectionsCount < limit) {
            Connection connection = new ManagedConnection(IP, port);
            connectionsCount++;
            return connection;
        } else isLimit = true;
        return null;
    }

    public ManagedConnection makeConnection(String IP, int port, String protocol) {
        if (connectionsCount < limit) {
            ManagedConnection connection = new ManagedConnection(IP, port, protocol);
            connectionsCount++;
            return connection;
        } else isLimit = true;
        return null;
    }

    public void setConnectionsCount(int connectionsCount) {
        this.connectionsCount = connectionsCount;
    }

    public int getConnectionsCount() {
        return this.connectionsCount;

    }

    public ManagedConnection getConnectionsByIPxPortxProtocol(String IP, String protocol) {
        ManagedConnection k = null;
        for (ManagedConnection i : connectionsList
                ) {
            if (i.getIP().equals(IP) && i.getProtocol().equals(protocol)) {
                k = i;
            }
        }
        return k;
    }

    public ManagedConnection getConnectionsByIPxPortxProtocol(String IP, int port) {
        ManagedConnection k = new ManagedConnection("xxx.xxx.xxx.xxx", -1, "none");
        for (ManagedConnection i : connectionsList
                ) {
            if (i.getIP().equals(IP) && i.getPort() == port) {
                k = i;
            }
        }
        return k;
    }

    public ManagedConnection getConnectionsByIPxPortxProtocol(String IP, int port, String protocol) {
        ManagedConnection k = new ManagedConnection("xxx.xxx.xxx.xxx", -1, "none");
        for (ManagedConnection i : connectionsList
                ) {
            if (i.getIP().equals(IP) && i.getPort() == port && i.getProtocol().equals(protocol)) {
                k = i;
            }
        }
        return k;
    }


    // BEGIN INNER CLASS ManagedConnection
    public class ManagedConnection implements Connection, Closeable {
        private boolean isOpen;
        private String IP;
        private int port;
        private String protocol;

        ManagedConnection(String IP, int port) {
            isOpen = true;
            this.IP = IP;
            this.port = port;
            this.protocol = "HTTP";
        }

        @Override
        public String toString() {
            return "Found existing connection " + this.IP + ":" + this.port + " " + this.protocol;
        }

        ManagedConnection(String IP, int port, String protocol) {
            isOpen = true;
            this.IP = IP;
            this.port = port;
            this.protocol = protocol;
        }


        public String getIP() {
            if (isOpen) {
                return this.IP;
            } else return "error";
        }

        public int getPort() {
            if (isOpen) {
                return this.port;
            } else return -1;
        }

        public String getProtocol() {
            if (isOpen) {
                return protocol;
            } else return "error";
        }

        public String connect() {
            this.isOpen = true;
            connectionsCount++;
            return "Connected to " + this.IP + ":" + this.port + " via " + this.protocol + ".";
        }

        public void close() {
            this.isOpen = false;
            connectionsCount--;

        }
    }
}
