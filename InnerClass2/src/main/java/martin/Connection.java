package martin;

/**
 * Created by matthewmartin on 2/5/17.
 */
public interface Connection {

        String connect();

        String getIP();

        String getProtocol();

        int getPort();

        void close();

}

