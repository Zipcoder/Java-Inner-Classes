package PracticingTDD;

import ConnectionManagement.IPOctet;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by alfatihmukhtar on 2/3/17.
 */
public class IPOctetTest {
    IPOctet octet = new IPOctet();

    @Test
    public void TestIPOctet() {
        int expected = 251;
        int actual = octet.getOctet();
        assertEquals("Not the right octet number",expected,actual);
    }
}
