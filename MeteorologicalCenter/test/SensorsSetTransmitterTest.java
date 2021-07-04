import org.junit.Assert;
import org.junit.Test;
import sensors.*;
import signalreceiver.OfficeDevice;
import transmitters.SensorsSetTransmitter;
import java.util.concurrent.TimeUnit;

public class SensorsSetTransmitterTest {
    private SensorsSetTransmitter sensorsSetTransmitter;

    @Test
    public void setSensorsSetTest() {
        WeatherStation weatherStation = new WeatherStation();
        sensorsSetTransmitter = new SensorsSetTransmitter(weatherStation.getNumSensors());
        weatherStation.detachSensorsSetTransmitter();
        weatherStation.attachSensorsSetTransmitter(sensorsSetTransmitter);
        Assert.assertEquals(weatherStation.getSensorsSetTransmitter(), sensorsSetTransmitter);
    }

    @Test
    public void removeSensorsSet() {
        WeatherStation weatherStation = new WeatherStation();
        sensorsSetTransmitter = weatherStation.getSensorsSetTransmitter();
        sensorsSetTransmitter.removeSensorsSet();
        Assert.assertNull(sensorsSetTransmitter.getSensorsSet());
        Assert.assertNull(weatherStation.getSensorsSetTransmitter());
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

    @Test
    public void getStateTest() throws  InterruptedException{
        WeatherStation weatherStation = new WeatherStation();
        IndoorMeter indoorMeter = new IndoorMeter();
        Barometer barometer = new Barometer();
        weatherStation.attachBarometer(barometer);
        Anemometer anemometer = new Anemometer();
        weatherStation.attachAnemometer(anemometer);
        WindVane windVane = new WindVane();
        weatherStation.attachWindVane(windVane);
        RainGauge rainGauge = new RainGauge();
        weatherStation.attachRainGauge(rainGauge);
        Thermometer thermometer = new Thermometer();
        weatherStation.attachThermometer(thermometer);
        Thermometer thermometer1 = new Thermometer();
        indoorMeter.attachThermometer(thermometer1);
        Hygrometer hygrometer = new Hygrometer();
        weatherStation.attachHygrometer(hygrometer);
        Hygrometer hygrometer1 = new Hygrometer();
        indoorMeter.attachHygrometer(hygrometer1);
        TimeUnit.SECONDS.sleep(1);
        float[] temp = weatherStation.getSensorsSetTransmitter().getState();
        float[] temp1 = indoorMeter.getSensorsSetTransmitter().getState();
        Assert.assertTrue(temp[0]>=980 && temp[0]<=1045);
        Assert.assertTrue(temp[1]>=0 && temp[1]<=150);
        Assert.assertTrue(temp[2]>=0 && temp[2]<=360);
        Assert.assertTrue(temp[3]>=0 && temp[3]<=26);
        Assert.assertTrue(temp[4]>=-5 && temp[4]<=40);
        Assert.assertTrue(temp[5]>=0 && temp[5]<=100);
        Assert.assertTrue(temp1[0]>=-5 && temp1[0]<=40);
        Assert.assertTrue(temp1[1]>=0 && temp1[1]<=100);
    }
}
