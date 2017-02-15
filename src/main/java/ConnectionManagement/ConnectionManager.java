package ConnectionManagement;

import java.io.Closeable;
import java.util.ArrayList;

/**
 * Created by alfatihmukhtar on 2/3/17.
 */
public class ConnectionManager {
    // FIELDS
    private int maxConnections;
    private ArrayList<ManagedConnection> activeConnections = new ArrayList<ManagedConnection>();

    // CONSTRUCTORS
    public ConnectionManager() { maxConnections = 5; }
    public ConnectionManager(int limit) {
        maxConnections = limit;
    }

    // GETTERS
    public String getActiveConnectionsLine() {
        String totalConnections = "";
        for(ManagedConnection connection : activeConnections) {
            totalConnections+=(connection.connect()+"\n");
        }
        return totalConnections;
    }
    public ArrayList<ManagedConnection> getActiveConnections() {
        return activeConnections;
    }

    // METHODS: Making Connection objects.
    public void makeConnection() {
        if(hasRoomForConnections()) {
            ManagedConnection connection = this.new ManagedConnection();
            activeConnections.add(connection);
        } else {
            reachedMaxConnections();
        }
    }
    public void makeConnection(String protocol) {
        if(hasRoomForConnections()) {
            ManagedConnection connection = this.new ManagedConnection(protocol);
            activeConnections.add(connection);
        } else {
            reachedMaxConnections();
        }
    }
    public void makeConnection(Integer port) {
        if(hasRoomForConnections()) {
            ManagedConnection connection = new ManagedConnection(port);
            activeConnections.add(connection);
        } else {
            reachedMaxConnections();
        }
    }

    // METHODS: Close a connection by index.
    public void closeConnection(int index) {
        activeConnections.get(index).close();
        activeConnections.remove(index);
    }

    // METHODS: Helper methods.
    public boolean hasRoomForConnections() {
        return activeConnections.size()<maxConnections;
    }
    private void reachedMaxConnections() {
        System.out.println("Sorry, but you cannot add more connections to this connection manager. " +
                "You have reached the limit.");
    }

    // INNER CLASS
    public class ManagedConnection implements Connection, Closeable {
        // FIELDS
        ProtocolsAndPorts protoPorts = new ProtocolsAndPorts();
        private String protocol;
        private Integer port;
        private IPOctet[] ipAddress = new IPOctet[4];
        private boolean physicalConnectionStatus;
        private boolean logicalConnectionStatus;
        private boolean closed;

        // CONSTRUCTOR
        public ManagedConnection() {
            protocol = "HTTP";
            setPort();
            setIPAddress();
            logicalConnectionStatus = true;
            physicalConnectionStatus = true;
            closed = false;
        }
        public ManagedConnection(String protocol) {
            this.protocol = protocol;
            physicalConnectionStatus = true;
            logicalConnectionStatus = true;
            setIPAddress();
            setPort();
            closed = false;
        }
        public ManagedConnection(Integer port) {
            this.port = port;
            physicalConnectionStatus = true;
            logicalConnectionStatus = true;
            setIPAddress();
            setProtocol();
            closed = false;
        }

        // SETTERS
        private void setIPAddress() {
            IPOctet octet;
            for(int i=0;i<4;i++) {
                octet = new IPOctet();
                ipAddress[i] = octet;
            }
        }
        private void setPort() {
            port = protoPorts.protocolToPort.get(protocol);
        }
        private void setProtocol() {
            protocol = protoPorts.portToProtocol.get(port);
        }

        // GETTERS
        public String getIPAddress() {
            String ipString = "";
            for(IPOctet octet : ipAddress) {
                ipString+=(octet.getOctet());
                if(octet!=ipAddress[3]) ipString+= ".";
            }
            return ipString;
        }
        public String getProtocol() { return protocol; }
        public int getPort() { return port; }
        public String getPhysicalConnectionStatus() {
            if(physicalConnectionStatus) return "Connected";
            return "Not Connected";
        }
        public String getLogicalConnectionStatus() {
            if(logicalConnectionStatus) return "Connected";
            return "Not Connected";
        }

        // IMPLEMENTED METHODS
        public String connect() {
            return "This connection's status is: " + getPhysicalConnectionStatus() +
                    "/" + getLogicalConnectionStatus() + ", and your IP address is " +
                    getIPAddress() + " using " + getProtocol() + " @ port number " +
                    getPort() + ".";
        }
        public void close() {
            protocol = null;
            port = 0;
            ipAddress = null;
            logicalConnectionStatus = false;
            physicalConnectionStatus = false;
            closed = true;
        }
    }
}
