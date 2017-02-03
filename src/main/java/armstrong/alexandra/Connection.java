package armstrong.alexandra;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by alexandraarmstrong on 2/2/17.
 */
public interface Connection extends Closeable {
    public void close();
    public String connect();
    public String getIP();
    public String getPort();
    public String getProtocol();
}
