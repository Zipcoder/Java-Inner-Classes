package collins.john;

/**
 * Created by johncollins on 2/3/17.
 */
public interface Connection extends Closeable {
    String getIP();

    int getPort();

    String getProtocol();

    String connect();

    void close();
}
