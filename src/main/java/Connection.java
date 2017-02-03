/**
 * Created by randallcrame on 2/2/17.
 */
public interface Connection {
    String connect();
    String getIp();
    int getPort();
    String getProtocol();
    void close();

}
