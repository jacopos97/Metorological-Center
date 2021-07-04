import org.junit.Assert;
import org.junit.Test;
import sensors.IndoorMeter;
import signalreceiver.OfficeDevice;

public class OfficeDeviceTest {

    @Test
    public void attachAndDetachIndoorMeterTest() {
        OfficeDevice officeDevice = new OfficeDevice(5000);
        Assert.assertNull(officeDevice.getObservedIndoorMeter());
        officeDevice.attachIndoorMeter(new IndoorMeter());
        Assert.assertNotNull(officeDevice.getObservedIndoorMeter());
        officeDevice.detachIndoorMeter();
        Assert.assertNull(officeDevice.getObservedIndoorMeter());
    }
}
