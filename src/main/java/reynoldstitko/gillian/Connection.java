package reynoldstitko.gillian;

import java.io.Closeable;

/**
 * Created by gillianreynolds-titko on 2/2/17.
 */
public interface Connection extends Closeable{

    //Define the interface
    String getIP(); //public modifier is redundant for interface methods
    int getPort();
    String getProtocol();

    String connect();
    void close();

}
