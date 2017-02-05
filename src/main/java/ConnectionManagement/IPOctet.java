package ConnectionManagement;

import java.util.Random;

/**
 * Created by alfatihmukhtar on 2/3/17.
 */
public class IPOctet {
    private Integer octet;
    private Integer maskValue;

    public IPOctet() {
        Random random = new Random();
        octet = random.nextInt(193) + 63;
    }

    public int getOctet() {
        return octet;
    }
}
