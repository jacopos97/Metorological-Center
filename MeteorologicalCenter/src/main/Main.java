package main;

import meteorologicalcenter.MeteorologicalCenter;
import sensors.*;
import signalreceiver.OfficeDevice;

public class Main {
    public static void main(String[] args) throws  InterruptedException{
        WeatherStation s = new WeatherStation();
        WeatherStation s1 = new WeatherStation();
        WeatherStation s2 = new WeatherStation();

        IndoorMeter indoorMeter = new IndoorMeter();
        OfficeDevice ter = new OfficeDevice(5000);
        ter.attachIndoorMeter(indoorMeter);
        IndoorMeter indoorMeter1 = new IndoorMeter();
        OfficeDevice ter1 = new OfficeDevice(6000);
        ter1.attachIndoorMeter(indoorMeter1);

        MeteorologicalCenter.getInstance().attachWeatherStation(s);
        MeteorologicalCenter.getInstance().attachWeatherStation(s1);
        MeteorologicalCenter.getInstance().attachWeatherStation(s2);
        Anemometer a = new Anemometer();
        s.attachAnemometer(a);
        Anemometer a1 = new Anemometer();
        s1.attachAnemometer(a1);
        Anemometer a2 = new Anemometer();
        s2.attachAnemometer(a2);
        Barometer b = new Barometer();
        s.attachBarometer(b);
        Barometer b1 = new Barometer();
        s1.attachBarometer(b1);
        Barometer b2 = new Barometer();
        s2.attachBarometer(b2);
        Hygrometer h = new Hygrometer();
        s.attachHygrometer(h);
        Hygrometer h1 = new Hygrometer();
        s1.attachHygrometer(h1);
        Hygrometer h2 = new Hygrometer();
        s2.attachHygrometer(h2);

        Hygrometer h3 = new Hygrometer();
        indoorMeter.attachHygrometer(h3);
        Hygrometer h4 = new Hygrometer();
        indoorMeter1.attachHygrometer(h4);

        RainGauge r = new RainGauge();
        s.attachRainGauge(r);
        RainGauge r1 = new RainGauge();
        s1.attachRainGauge(r1);
        RainGauge r2 = new RainGauge();
        s2.attachRainGauge(r2);
        Thermometer t = new Thermometer();
        s.attachThermometer(t);
        Thermometer t1 = new Thermometer();
        s1.attachThermometer(t1);
        Thermometer t2 = new Thermometer();
        s2.attachThermometer(t2);

        Thermometer t3 = new Thermometer();
        indoorMeter.attachThermometer(t3);
        Thermometer t4 = new Thermometer();
        indoorMeter1.attachThermometer(t4);


        WindVane w = new WindVane();
        s.attachWindVane(w);
        WindVane w1 = new WindVane();
        s1.attachWindVane(w1);
        WindVane w2 = new WindVane();
        s2.attachWindVane(w2);
        Thread.sleep(15000);

        indoorMeter.interruptSensorsSetTransmitter();
        indoorMeter.interruptSensorsSet();
        indoorMeter1.interruptSensorsSetTransmitter();
        indoorMeter1.interruptSensorsSet();

        s.interruptSensorsSetTransmitter();
        s.interruptSensorsSet();
        s1.interruptSensorsSetTransmitter();
        s2.interruptSensorsSetTransmitter();
        s1.interruptSensorsSet();
        s2.interruptSensorsSet();
        MeteorologicalCenter.getInstance().interruptMeteorologicalCenterTransmitter();

    }
}
