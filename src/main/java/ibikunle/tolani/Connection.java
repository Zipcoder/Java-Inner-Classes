package ibikunle.tolani;

import java.io.Closeable;

/**
 * Created by tolaniibikunle on 2/3/17.
 */
public interface Connection extends Closeable {
    String iPAddress = " ";
    String protocol = " ";
    String port = " ";
    String status = " ";

    String getIPAddress();

     String getProtocol();

     String getPort();

     String connect();


     void close();
}
