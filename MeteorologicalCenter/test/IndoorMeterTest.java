import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sensors.*;
import transmitters.SensorsSetTransmitter;

public class IndoorMeterTest {
    private IndoorMeter indoorMeter;

    @Before
    public void initialize() {
        indoorMeter = new IndoorMeter();
    }

    @Test
    public void getNumSensorsTest() {
        Assert.assertEquals(indoorMeter.getNumSensors(), 2);
    }

    @Test
    public void detachSensorsSetTransmitterTest() {
        Assert.assertNotNull(indoorMeter.getSensorsSetTransmitter());
        indoorMeter.detachSensorsSetTransmitter();
        Assert.assertNull(indoorMeter.getSensorsSetTransmitter());
    }

    @Test
    public void attachSensorsSetTransmitterTest() {
        indoorMeter.detachSensorsSetTransmitter();
        indoorMeter.attachSensorsSetTransmitter(new SensorsSetTransmitter(indoorMeter.getNumSensors()));
        Assert.assertNotNull(indoorMeter.getSensorsSetTransmitter());
    }

    @Test
    public void attachThermometerTest() {
        Thermometer thermometer = new Thermometer();
        Assert.assertNull(indoorMeter.getThermometer());
        indoorMeter.attachThermometer(thermometer);
        Assert.assertEquals(indoorMeter.getThermometer(), thermometer);
    }

    @Test
    public void detachThermometerTest() {
        Thermometer thermometer = new Thermometer();
        indoorMeter.attachThermometer(thermometer);
        indoorMeter.detachThermometer();
        Assert.assertNull(indoorMeter.getThermometer());
    }

    @Test
    public void attachHygrometerTest() {
        Hygrometer hygrometer = new Hygrometer();
        Assert.assertNull(indoorMeter.getHygrometer());
        indoorMeter.attachHygrometer(hygrometer);
        Assert.assertEquals(indoorMeter.getHygrometer(), hygrometer);
    }

    @Test
    public void detachHygrometerTest() {
        Hygrometer hygrometer = new Hygrometer();
        indoorMeter.attachHygrometer(hygrometer);
        indoorMeter.detachHygrometer();
        Assert.assertNull(indoorMeter.getHygrometer());
    }
}
