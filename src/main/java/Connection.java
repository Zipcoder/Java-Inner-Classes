import java.io.Closeable;

/**
 * Created by randallcrame on 2/2/17.
 */
public interface Connection extends Closeable{
    String connect();
    String getIP();
    int getPort();
    String getProtocol();
    boolean isClosed();

}
