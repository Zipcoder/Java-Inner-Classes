package PracticingTDD;

import ConnectionManagement.ProtocolsAndPorts;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by alfatihmukhtar on 2/3/17.
 */
public class ProtocolsAndPortsTest {
    ProtocolsAndPorts testExample = new ProtocolsAndPorts();

    @Test
    public void TestSetHashMapForProtocol() {
        Integer expected = 80;
        Integer actual = testExample.protocolToPort.get("HTTP");
        assertEquals("This is not the correct protocol my friends",expected,actual);
    }

    @Test
    public void TestSetHashMapForPort() {
        String expected = "Telnet";
        String actual = testExample.portToProtocol.get(23);
        assertEquals("This is not the correct port my friends",expected,actual);
    }
}
