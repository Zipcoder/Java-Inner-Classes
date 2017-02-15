package ConnectionManagement;

import java.util.HashMap;

/**
 * Created by alfatihmukhtar on 2/3/17.
 */
public class ProtocolsAndPorts {
    // FIELD: A hashmap containing protocols, paired with port numbers.
    public HashMap<String, Integer> protocolToPort = new HashMap<String, Integer>();
    public HashMap<Integer, String> portToProtocol = new HashMap<Integer, String>();


    // CONSTRUCTOR
    public ProtocolsAndPorts() {
        setProtocolToPort();
        setPortToProtocol();
    }

    // METHOD: Building the hashmap.
    public void setProtocolToPort() {
        protocolToPort.put("HTTP", 80);
        protocolToPort.put("HTTPS", 443);
        protocolToPort.put("Telnet", 23);
        protocolToPort.put("SSH", 22);
    }
    public void setPortToProtocol() {
        portToProtocol.put(80,"HTTP");
        portToProtocol.put(443,"HTTPS");
        portToProtocol.put(23,"Telnet");
        portToProtocol.put(22,"SSH");
    }

}
