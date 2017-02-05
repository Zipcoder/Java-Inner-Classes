package humphrey.jeriah.JavaInnerClasses;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jeriahhumphrey on 2/3/17.
 */

interface Connection extends Closeable {


    String connect();
    String getIp();
    int getPort();
    String getProtocol();

}
public class ConnectionManager {
   private ArrayList <Connection> connectList = new ArrayList<Connection>();
    private static final int MAXCONNECTION = 3;
    private int connectionCount=0;


    public ArrayList<Connection> getList(){
        return  connectList;
    }

    public Connection buildConnection(String ipAdress,int port ){
        if(!checkMax()) {
            ManagedConnection m1 = this.new ManagedConnection(ipAdress, port);
            for (int i = 0; i < connectList.size(); i++) {
                if (m1.ipConflict(connectList.get(i).getIp())) {
                    System.out.println("error: IP conflict");
                    m1 = null;
                    return m1;
                }
            }

                connectList.add(m1);
                    connectionCount++;
                    return m1;
                }

        else{
            System.out.println("No more connections availalbe");
            return null;
        }
    }
    public Connection buildConnection (String ipAdress,int port, String protocol ) {
        if (!checkMax()) {
            ManagedConnection m1 = this.new ManagedConnection(ipAdress, port, protocol);
            {
                for(int i = 0; i<connectList.size();i++){
                    if(m1.ipConflict(connectList.get(i).getIp())){
                        System.out.println("error: IP conflict");
                        m1 = null;
                        return m1;
                    }
                }
                m1.connect();
                if (m1.getProtocol().equals("invalid protocol")) {
                    m1 = null;
                    return m1;
                }
                else {
                    connectList.add(m1);
                    connectionCount++;
                    return m1;
                }
            }
        }
        else{
                System.out.println("No more connections availalbe");
            connectionCount= connectList.size();
                return null;
            }

        }


    public String getConnectionbyIPAndProtocol(String ip, String protocol) {
        String connection= "";
        Connection c = null;
        for (int i = 0; i < connectList.size(); i++) {
            if (connectList.get(i).getIp().equals(ip) && connectList.get(i).getProtocol().equals(protocol)) {
                c = connectList.get(i);
                connection= c.connect();
            }
            else{
                connection = "This connection does not exist";
            }
        }
        System.out.println(connection);
            return connection;
    }


    public int getOpenConnectionCount(){
        return connectionCount;
    }



    public boolean checkMax(){
        if (connectionCount>=MAXCONNECTION) {
            return true;
        }
        else{
            return false;
        }
    }


    private class ManagedConnection implements Connection {
        String ip;
        int port;
        String protocol;
        boolean closeStatus= false;

        public ManagedConnection(){
            ip = "1.0.0.0";
            port= 80;
            protocol = "HTTP";

        }

        public ManagedConnection(String ip, int port, String protocol) {
            this.setIp(ip);
            this.setPort(port);
            this.setProtocol(protocol);

        }
        public ManagedConnection(String ip, int port ) {
            this.setIp(ip);
            this.setPort(port);
            this.protocol="HTTP";

        }


        public String connect() {
            String connection;
            if(closeStatus ){
                connection= "error: connection closed";
            }
            else if (protocol.equals("invalid protocol")){
                connection = "error invalid protocol";
            }
            else
        {
            connection = "Connected to " + this.getIp() + ":" + this.getPort() + " via " + this.getProtocol() + ".";
        }
            System.out.println(connection);
        return connection;
        }


        public String setIp(String ip) {
            this.ip = ip;
            return this.ip;
        }

        public int setPort(int port) {
            this.port = port;
            return this.port;
        }


        public String setProtocol(String protocol) {
            switch(protocol){
                case "HTTP":
                    this.protocol = protocol;
                    break;
                case "FTP":
                    this.protocol = protocol;
                    break;
                case "SMTP":
                    this.protocol = protocol;
                    break;
                default:
                    this.protocol = "invalid protocol";
            }
                return protocol;
        }
        public String getIp() {
            if (closeStatus) {
                return "invalid";
            } else {

                return ip;
            }
        }

        public int getPort() {
            if (closeStatus) {
                System.out.print("invalid ");
                return 0;
            } else {
                return port;
            }
        }

        public String getProtocol() {
            if (closeStatus) {
                return "invalid";
            } else {
                return protocol;
            }
        }

        public boolean ipConflict(String ip){
            if(ip.equals(this.ip)){
                return true;
            }
            return false;
        }


        public void close() {
            System.out.println("Connection closed: " + this.getIp() + ":" + this.getPort() +
                    " via " + this.getProtocol() + ".");
            closeStatus=true;
            connectionCount--;
        }

    }

    }
