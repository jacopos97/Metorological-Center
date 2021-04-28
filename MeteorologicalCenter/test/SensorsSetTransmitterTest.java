import org.junit.Assert;
import org.junit.Test;
import sensors.IndoorMeter;
import sensors.WeatherStation;
import signalreceiver.OfficeDevice;
import transmitters.SensorsSetTransmitter;

public class SensorsSetTransmitterTest {
    private SensorsSetTransmitter sensorsSetTransmitter;

    @Test
    public void setSensorsSetTest() {
        WeatherStation weatherStation = new WeatherStation();
        sensorsSetTransmitter = new SensorsSetTransmitter(weatherStation.getNumSensors());
        Assert.assertTrue(sensorsSetTransmitter.setSensorsSet(weatherStation));
    }

    @Test
    public void addObserverTest() {
        OfficeDevice officeDevice1 = new OfficeDevice(3000);
        OfficeDevice officeDevice2 = new OfficeDevice(500);
        IndoorMeter indoorMeter = new IndoorMeter();
        sensorsSetTransmitter = indoorMeter.getSensorsSetTransmitter();
        Assert.assertEquals(sensorsSetTransmitter.countObservers(),0);
        sensorsSetTransmitter.addObserver(officeDevice1);
        Assert.assertEquals(sensorsSetTransmitter.countObservers(),1);
        sensorsSetTransmitter.addObserver(officeDevice2);
        Assert.assertEquals(sensorsSetTransmitter.countObservers(),1);
    }

    //TODO: finisci i public del SensorsSetTransmitter e fai anche quelli del Transmitter se ce ne sono
}
