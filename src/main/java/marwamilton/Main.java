package marwamilton;

/**
 * Created by mkulima on 2/3/17.
 */
public class Main {

    public static void main(String[] args) {
        //outerManagedConnection managedConnection1 = new outerManagedConnection();
        //System.out.println(managedConnection1.connect());

        ConnectionManager connectionManager = new ConnectionManager(6);
        connectionManager.createNewConnection("261.001.001.001",2000, Protocol.HTTP);
        connectionManager.createNewConnection("261.001.001.001",2001, Protocol.HTTP);
        connectionManager.createNewConnection("261.001.001.001",2002, Protocol.HTTP);
        connectionManager.createNewConnection("261.001.001.001",2003, Protocol.HTTP);
        connectionManager.createNewConnection("261.001.001.001",2004, Protocol.HTTP);
        connectionManager.createNewConnection();
        // print list of all connections
        System.out.println(connectionManager.listAllManagedConnections());

        // print connections matched by Port
        System.out.println();
        System.out.println("Test getConnection method");
        System.out.println(connectionManager.getConnection(2000));

        // print connections matched by IP
        System.out.println();
        System.out.println(connectionManager.getConnection("261.001.001.001"));

        // print all connections after closing a couple
        connectionManager.closeConnection("261.001.001.001",2002, Protocol.HTTP);
        System.out.println();
        System.out.println("closed port 2002");
        System.out.println(connectionManager.listAllManagedConnections());
    }

}
