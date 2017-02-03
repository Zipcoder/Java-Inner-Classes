package marwamilton;

import java.io.Closeable;

/**
 * Created by mkulima on 2/2/17.
 */
public interface Connection extends Closeable{

    String getIP();
    int getPort();
    Protocol getProtocol();
    String connect();
    ConnectionStatus getConnectionStatus();

    @Override
    void close();

    String toString();
}
