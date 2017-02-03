package battin.preston;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prestonbattin on 2/2/17.
 */
public class ConnectionManager {

    private final int CONNECTION_LIMIT = 10;
    private static int numberOfConections = 0;
    List<Connection> connections = new ArrayList();


    public ManagedConnection getManagedConnection(String IP, String port, Protocol.Proto protocol) {

        return new ManagedConnection(IP, port, protocol);
    }

    public int getCONNECTION_LIMIT() {
        return CONNECTION_LIMIT;
    }

    public Connection getConnection(String IP, Protocol.Proto protocol) {

        for (int i = 0; i < numberOfConections; i++) {

            if (connections.get(i).getIP().equals(IP) && connections
                    .get(i).getProtocol().equals(protocol)) {

                return connections.get(i);
            }

        }
        return null;
    }



    public class ManagedConnection implements Connection, Closeable{

        private String IpAddress;
        private String port;
        private Protocol.Proto protocol;
        private String status;

       public void addToConnectionsList() {

           boolean status = true;

           for(int i = 0; i < connections.size(); i++){

               if (numberOfConections <= CONNECTION_LIMIT && connections.get(i).getStatus().equals("CLOSED")){

                   connections.set(i, this);

               }

           }
           if (numberOfConections < CONNECTION_LIMIT) {
               connections.add(this);
               numberOfConections++;
           }

             else

               for(int i = 0; i < numberOfConections; i++) {

                    if (connections.get(i).getStatus().equals("CLOSED"))

                         status = false;
               }

               if(!status)
                   System.out.println("Error. Too May Connections");

           }



        ManagedConnection(String IP, String port, Protocol.Proto protocol){

               this.IpAddress = IP;
               this.port = port;
               this.protocol = protocol;
               this.status = "OPEN";

               addToConnectionsList();

        }


        public String getStatus() {

            return this.status;
        }


        @Override
        public String getIP(){

            if(this.status.equals("CLOSED")){
                return "Error CLOSED";
            }
            return this. IpAddress;
        }

        @Override
        public Protocol.Proto getProtocol(){

            if(this.status.equals("CLOSED")){
                System.out.println("Error CLOSED");
                return null;
            }
            return this.protocol;

        }

        @Override
        public String connect(){

            if(this.status.equals("CLOSED")){
                return "Error CLOSED";
            }
           return "Connected to " + IpAddress + ":" + port + " via " +
                   protocol;
        };

        @Override
        public String getPort(){

            if(this.status.equals("CLOSED")){
                return "Error CLOSED";
            }
            return this.port;
        }

        @Override
        public void close(){

            this.status = "CLOSED";
            this.protocol = null;
        }

    }
}
