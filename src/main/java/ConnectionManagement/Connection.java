package ConnectionManagement;

/**
 * Created by alfatihmukhtar on 2/3/17.
 */
public interface Connection {
    public String connect();
    public String getPhysicalConnectionStatus();
    public String getLogicalConnectionStatus();
    public String getIPAddress();
    public String getProtocol();
    public int getPort();
    public void close();
}
