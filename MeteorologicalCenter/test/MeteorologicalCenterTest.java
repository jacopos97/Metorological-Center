import meteorologicalcenter.MeteorologicalCenter;
import org.junit.Assert;
import org.junit.Test;
import sensors.WeatherStation;
import transmitters.MeteorologicalCenterTransmitter;

public class MeteorologicalCenterTest {
    @Test
    public void attachAndDetachWeatherStationTest() {
        WeatherStation weatherStation = new WeatherStation();
        Assert.assertEquals(MeteorologicalCenter.getInstance().getObservedWeatherStation().size(), 0);
        MeteorologicalCenter.getInstance().attachWeatherStation(weatherStation);
        Assert.assertEquals(MeteorologicalCenter.getInstance().getObservedWeatherStation().size(), 1);
        MeteorologicalCenter.getInstance().detachWeatherStation(weatherStation);
        Assert.assertEquals(MeteorologicalCenter.getInstance().getObservedWeatherStation().size(), 0);
    }

    @Test
    public void attachAndDetachMeteorologicalCenterTransmitter() {
        Assert.assertNotNull(MeteorologicalCenter.getInstance().getMeteorologicalCenterTransmitter());
        MeteorologicalCenter.getInstance().detachMeteorologicalCenterTransmitter();
        Assert.assertNull(MeteorologicalCenter.getInstance().getMeteorologicalCenterTransmitter());
        MeteorologicalCenter.getInstance().attachMeteorologicalCenterTransmitter(new MeteorologicalCenterTransmitter());
        Assert.assertNotNull(MeteorologicalCenter.getInstance().getMeteorologicalCenterTransmitter());
    }
}
