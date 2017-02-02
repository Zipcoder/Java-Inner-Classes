package mattern.william;

import java.io.Closeable;

/**
 * Created by williammattern on 2/2/17.
 */
public interface Connection{
    String connect();

    String getIP();

    String getProtocol();

    int getPort();
}
