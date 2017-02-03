package battin.preston;

import java.io.Closeable;

/**
 * Created by prestonbattin on 2/2/17.
 */
public interface Connection extends Closeable{

    public String getIP();
    public String getPort();
    public Protocol.Proto getProtocol();
    public String connect();
    public void close();
    public String getStatus();
}
