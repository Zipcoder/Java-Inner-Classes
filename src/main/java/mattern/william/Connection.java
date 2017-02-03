package mattern.william;

/**
 * Created by williammattern on 2/2/17.
 */
public interface Connection{
    String connect();

    String getIP();

    Protocol getProtocol();

    int getPort();
}
