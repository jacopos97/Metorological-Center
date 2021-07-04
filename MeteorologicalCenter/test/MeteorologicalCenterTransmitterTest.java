import meteorologicalcenter.MeteorologicalCenter;
import org.junit.Assert;
import org.junit.Test;
import sensors.*;
import java.util.concurrent.TimeUnit;

public class MeteorologicalCenterTransmitterTest {

    @Test
    public void getStateTest() throws InterruptedException{
        WeatherStation weatherStation = new WeatherStation();
        Assert.assertEquals(MeteorologicalCenter.getInstance().getObservedWeatherStation().size(), 0);
        MeteorologicalCenter.getInstance().attachWeatherStation(weatherStation);
        Assert.assertEquals(MeteorologicalCenter.getInstance().getObservedWeatherStation().size(), 1);
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
        TimeUnit.SECONDS.sleep(2);
        float[] temp = MeteorologicalCenter.getInstance().getMeteorologicalCenterTransmitter().getState();
        Assert.assertTrue(temp[0]>=980 && temp[0]<=1045);
        Assert.assertTrue(temp[1]>=0 && temp[1]<=150);
        Assert.assertTrue(temp[2]>=0 && temp[2]<=360);
        Assert.assertTrue(temp[3]>=0 && temp[3]<=26);
        Assert.assertTrue(temp[4]>=-5 && temp[4]<=40);
        Assert.assertTrue(temp[5]>=0 && temp[5]<=100);
    }
}
