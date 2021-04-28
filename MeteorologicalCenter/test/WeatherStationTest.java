import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sensors.*;
import transmitters.SensorsSetTransmitter;

public class WeatherStationTest {
    private WeatherStation weatherStation;

    @Before
    public void initialize(){
        weatherStation = new WeatherStation();
    }

    @Test
    public void getNumSensorsTest() {
        Assert.assertEquals(weatherStation.getNumSensors(), 6);
    }

    @Test
    public void detachSensorsSetTransmitterTest() {
        Assert.assertNotNull(weatherStation.getSensorsSetTransmitter());
        weatherStation.detachSensorsSetTransmitter();
        Assert.assertNull(weatherStation.getSensorsSetTransmitter());
    }

    @Test
    public void attachSensorsSetTransmitterTest() {
        weatherStation.detachSensorsSetTransmitter();
        weatherStation.attachSensorsSetTransmitter(new SensorsSetTransmitter(weatherStation.getNumSensors()));
        Assert.assertNotNull(weatherStation.getSensorsSetTransmitter());

    }

    @Test
    public void attachBarometerTest() {
        Barometer barometer = new Barometer();
        Assert.assertNull(weatherStation.getBarometer());
        weatherStation.attachBarometer(barometer);
        Assert.assertEquals(weatherStation.getBarometer(), barometer);
    }

    @Test
    public void detachBarometerTest() {
        Barometer barometer = new Barometer();
        weatherStation.attachBarometer(barometer);
        weatherStation.detachBarometer();
        Assert.assertNull(weatherStation.getBarometer());
    }

    @Test
    public void attachAnemometerTest() {
        Anemometer anemometer = new Anemometer();
        Assert.assertNull(weatherStation.getAnemometer());
        weatherStation.attachAnemometer(anemometer);
        Assert.assertEquals(weatherStation.getAnemometer(), anemometer);
    }

    @Test
    public void detachAnemometerTest() {
        Anemometer anemometer = new Anemometer();
        weatherStation.attachAnemometer(anemometer);
        weatherStation.detachAnemometer();
        Assert.assertNull(weatherStation.getAnemometer());
    }

    @Test
    public void attachWindVaneTest() {
        WindVane windVane = new WindVane();
        Assert.assertNull(weatherStation.getWindVane());
        weatherStation.attachWindVane(windVane);
        Assert.assertEquals(weatherStation.getWindVane(), windVane);
    }

    @Test
    public void detachWindVaneTest() {
        WindVane windVane = new WindVane();
        weatherStation.attachWindVane(windVane);
        weatherStation.detachWindVane();
        Assert.assertNull(weatherStation.getWindVane());
    }

    @Test
    public void attachRainGaugeTest() {
        RainGauge rainGauge = new RainGauge();
        Assert.assertNull(weatherStation.getRainGauge());
        weatherStation.attachRainGauge(rainGauge);
        Assert.assertEquals(weatherStation.getRainGauge(), rainGauge);
    }

    @Test
    public void detachRainGaugeTest() {
        RainGauge rainGauge = new RainGauge();
        weatherStation.attachRainGauge(rainGauge);
        weatherStation.detachRainGauge();
        Assert.assertNull(weatherStation.getRainGauge());
    }

    @Test
    public void attachThermometerTest() {
        Thermometer thermometer = new Thermometer();
        Assert.assertNull(weatherStation.getThermometer());
        weatherStation.attachThermometer(thermometer);
        Assert.assertEquals(weatherStation.getThermometer(), thermometer);
    }

    @Test
    public void detachThermometerTest() {
        Thermometer thermometer = new Thermometer();
        weatherStation.attachThermometer(thermometer);
        weatherStation.detachThermometer();
        Assert.assertNull(weatherStation.getThermometer());
    }

    @Test
    public void attachHygrometerTest() {
        Hygrometer hygrometer = new Hygrometer();
        Assert.assertNull(weatherStation.getHygrometer());
        weatherStation.attachHygrometer(hygrometer);
        Assert.assertEquals(weatherStation.getHygrometer(), hygrometer);
    }

    @Test
    public void detachHygrometerTest() {
        Hygrometer hygrometer = new Hygrometer();
        weatherStation.attachHygrometer(hygrometer);
        weatherStation.detachHygrometer();
        Assert.assertNull(weatherStation.getHygrometer());
    }

    /*@Test
    public void getSensorsValueTest() throws InterruptedException {
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
        Hygrometer hygrometer = new Hygrometer();
        weatherStation.attachHygrometer(hygrometer);
        float[] temp = weatherStation.getSensorsValue();
        Assert.assertTrue(temp[0]>=980 && temp[0]<=1045);
        Assert.assertTrue(temp[1]>=0 && temp[1]<=150);
        Assert.assertTrue(temp[2]>=0 && temp[2]<=360);
        Assert.assertTrue(temp[3]>=0 && temp[3]<=26);
        Assert.assertTrue(temp[4]>=-5 && temp[4]<=40);
        Assert.assertTrue(temp[5]>=0 && temp[5]<=100);
    }*/
}
