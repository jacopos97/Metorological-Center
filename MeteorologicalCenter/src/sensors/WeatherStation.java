package sensors;

public class WeatherStation extends SensorsSet {
    private static final String type = "WeatherStation";
    private static final int barometerPos = 0;
    private static final int anemometerPos = 1;
    private static final int windVanePos = 2;
    private static final int rainGaugePos = 3;
    private static final int thermometerPos = 4;
    private static final int hygrometerPos = 5;
    private static final int maxDevice = 6;
    private static int numWeatherStation = 0;
    private Barometer barometer;
    private Anemometer anemometer;
    private WindVane windVane;
    private RainGauge rainGauge;
    private Thermometer thermometer;
    private Hygrometer hygrometer;

    public WeatherStation(){
        super(maxDevice, numWeatherStation, type);
        numWeatherStation++;
        barometer = null;
        anemometer = null;
        windVane = null;
        rainGauge = null;
        thermometer = null;
        hygrometer = null;
    }

    public void attachBarometer(Barometer barometer) {
        if (this.barometer == null) {
            this.barometer = barometer;
            this.barometer.setSensorsSet(this);
            super.setSensor(this.barometer.getActualValue(), barometerPos);
            this.barometer.startSensor();
        }
        else
            System.out.println("Error!!! This "+type+" already has a barometer.");
    }

    public void detachBarometer() {
        if (barometer != null){
            this.barometer.interruptSensor();
            this.barometer.removeSensorSet();
            this.barometer = null;
            super.removeSensor(barometerPos);
        }
        else
            System.out.println("Error!!! This "+type+" hasn't a barometer to detach yet.");
    }

    public void attachAnemometer(Anemometer anemometer) {
        if (this.anemometer == null){
            this.anemometer = anemometer;
            this.anemometer.setSensorsSet(this);
            super.setSensor(this.anemometer.getActualValue(), anemometerPos);
            this.anemometer.startSensor();
        }
        else
            System.out.println("Error!!! This "+type+" already has a anemometer.");
    }

    public void detachAnemometer() {
        if (anemometer != null) {
            this.anemometer.interruptSensor();
            this.anemometer.removeSensorSet();
            this.anemometer = null;
            super.removeSensor(anemometerPos);
        }
        else
            System.out.println("Error!!! This station hasn't a anemometer to detach yet.");
    }

    public void attachWindVane(WindVane windVane) {
        if (this.windVane == null) {
            this.windVane = windVane;
            this.windVane.setSensorsSet(this);
            super.setSensor(this.windVane.getActualValue(), windVanePos);
            this.windVane.startSensor();
        }
        else
            System.out.println("Error!!! This "+type+" already has a wind vane.");
    }

    public void detachWindVane() {
        if (windVane != null) {
            this.windVane.interruptSensor();
            this.windVane.removeSensorSet();
            this.windVane = null;
            super.removeSensor(windVanePos);
        }
        else
            System.out.println("Error!!! This "+type+" hasn't a wind vane to detach yet.");
    }

    public void attachRainGauge(RainGauge rainGauge) {
        if (this.rainGauge == null) {
            this.rainGauge = rainGauge;
            this.rainGauge.setSensorsSet(this);
            super.setSensor(this.rainGauge.getActualValue(), rainGaugePos);
            this.rainGauge.startSensor();
        }
        else
            System.out.println("Error!!! This "+type+" already has a rain gauge.");
    }

    public void detachRainGauge() {
        if (rainGauge != null) {
            this.rainGauge.interruptSensor();
            this.rainGauge.removeSensorSet();
            this.rainGauge = null;
            super.removeSensor(rainGaugePos);
        }
        else
            System.out.println("Error!!! This "+type+" hasn't a rain gauge to detach yet.");
    }

    public void attachThermometer(Thermometer thermometer) {
        if (this.thermometer == null) {
            this.thermometer = thermometer;
            this.thermometer.setSensorsSet(this);
            super.setSensor(this.thermometer.getActualValue(), thermometerPos);
            this.thermometer.startSensor();
        }
        else
            System.out.println("Error!!! This "+type+" already has a thermometer.");
    }

    public void detachThermometer() {
        if (thermometer != null) {
            this.thermometer.interruptSensor();
            this.thermometer.removeSensorSet();
            this.thermometer = null;
            super.removeSensor(thermometerPos);
        }
        else
            System.out.println("Error!!! This "+type+" hasn't a thermometer to detach yet.");
    }

    public void attachHygrometer(Hygrometer hygrometer) {
        if (this.hygrometer == null) {
            this.hygrometer = hygrometer;
            this.hygrometer.setSensorsSet(this);
            super.setSensor(this.hygrometer.getActualValue(), hygrometerPos);
            this.hygrometer.startSensor();
        }
        else
            System.out.println("Error!!! This "+type+" already has a hygrometer.");
    }

    public void detachHygrometer() {
        if (hygrometer != null){
            this.hygrometer.interruptSensor();
            this.hygrometer.removeSensorSet();
            this.hygrometer = null;
            super.removeSensor(hygrometerPos);
        }
        else
            System.out.println("Error!!! This "+type+" hasn't a hygrometer to detach yet.");
    }

    public static String getSensorType(int pos) {
        if (pos == barometerPos)
            return Barometer.getType();
        else if (pos == anemometerPos)
            return Anemometer.getType();
        else if (pos == windVanePos)
            return WindVane.getType();
        else if (pos == rainGaugePos)
            return RainGauge.getType();
        else if (pos == thermometerPos)
            return  Thermometer.getType();
        else if (pos == hygrometerPos)
            return Hygrometer.getType();
        else
            return null;
    }

    public void interruptSensorsSet() {
        barometer.interruptSensor();
        anemometer.interruptSensor();
        windVane.interruptSensor();
        rainGauge.interruptSensor();
        thermometer.interruptSensor();
        hygrometer.interruptSensor();
    }

    int getSensorPos(SensorID sensorID) {
        if (SensorID.areEqual(sensorID, barometer.getSensorID()))
            return barometerPos;
        else if (SensorID.areEqual(sensorID, anemometer.getSensorID()))
            return anemometerPos;
        else if (SensorID.areEqual(sensorID, windVane.getSensorID()))
            return windVanePos;
        else if (SensorID.areEqual(sensorID, rainGauge.getSensorID()))
            return rainGaugePos;
        else if (SensorID.areEqual(sensorID, thermometer.getSensorID()))
            return thermometerPos;
        else if (SensorID.areEqual(sensorID, hygrometer.getSensorID()))
            return hygrometerPos;
        return super.getNumSensors();
    }

    boolean isFull() {
        boolean full = false;
        if (barometer!=null && anemometer!=null && windVane!=null && rainGauge!=null && thermometer!=null && hygrometer!=null)
            full = true;
        return full;
    }

    public static int getDevicePos(String deviceType) {
        switch (deviceType) {
            case "Barometer":
                return barometerPos;
            case "Anemometer":
                return anemometerPos;
            case "WindVane":
                return windVanePos;
            case "RainGauge":
                return rainGaugePos;
            case "Thermometer":
                return thermometerPos;
            case "Hygrometer":
                return hygrometerPos;
            default:
                return maxDevice;
        }
    }

    public Barometer getBarometer() {
        return barometer;
    }

    public Anemometer getAnemometer() {
        return anemometer;
    }

    public WindVane getWindVane() {
        return windVane;
    }

    public RainGauge getRainGauge() {
        return rainGauge;
    }

    public Thermometer getThermometer() {
        return thermometer;
    }

    public Hygrometer getHygrometer() {
        return hygrometer;
    }
}
