public class WeatherStationSet extends SensorsSet {
    private static final int numSensors = 6;
    private Barometer barometer;
    private Anemometer anemometer;
    private WindVane windVane;
    private RainGauge rainGauge;
    private Thermometer thermometer;
    private Hygrometer hygrometer;

    public WeatherStationSet(){
        super(numSensors);
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
            this.barometer.generateValue();
            super.setSensor(this.barometer.getSensorID(), this.barometer.getActualValue(), 0);
            this.barometer.start();
        }
        else
            System.out.println("Error!!! This station already has a barometer.");//TODO: è scritto station, ma in verità questo set è genereale e riusabile, perciò il tipo prendilo dalla classe WeatherStation. Correggi anche gli altri.
    }

    public void detachBarometer() {
        if (barometer != null){
            this.barometer.interrupt();
            this.barometer.removeSensorSet();
            this.barometer = null;
            super.removeSensor(0);
        }
        else
            System.out.println("Error!!! This station hasn't a barometer to detach yet.");
    }

    public void attachAnemometer(Anemometer anemometer) {
        if (this.anemometer == null){
            this.anemometer = anemometer;
            this.anemometer.setSensorsSet(this);
            this.anemometer.generateValue();
            super.setSensor(this.anemometer.getSensorID(), this.anemometer.getActualValue(), 1);
            this.anemometer.start();
        }
        else
            System.out.println("Error!!! This station already has a anemometer.");
    }

    public void detachAnemometer() {
        if (anemometer != null) {
            this.anemometer.interrupt();
            this.anemometer.removeSensorSet();
            this.anemometer = null;
            super.removeSensor(1);
        }
        else
            System.out.println("Error!!! This station hasn't a anemometer to detach yet.");
    }

    public void attachWindVane(WindVane windVane) {
        if (this.windVane == null) {
            this.windVane = windVane;
            this.windVane.setSensorsSet(this);
            this.windVane.generateValue();
            super.setSensor(this.windVane.getSensorID(), this.windVane.getActualValue(), 2);
            this.windVane.start();
        }
        else
            System.out.println("Error!!! This station already has a wind vane.");
    }

    public void detachWindVane() {
        if (windVane != null) {
            this.windVane.interrupt();
            this.windVane.removeSensorSet();
            this.windVane = null;
            super.removeSensor(2);
        }
        else
            System.out.println("Error!!! This station hasn't a wind vane to detach yet.");
    }

    public void attachRainGauge(RainGauge rainGauge) {
        if (this.rainGauge == null) {
            this.rainGauge = rainGauge;
            this.rainGauge.setSensorsSet(this);
            this.rainGauge.generateValue();
            super.setSensor(this.rainGauge.getSensorID(), this.rainGauge.getActualValue(), 3);
            this.rainGauge.start();
        }
        else
            System.out.println("Error!!! This station already has a rain gauge.");
    }

    public void detachRainGauge() {
        if (rainGauge != null) {
            this.rainGauge.interrupt();
            this.rainGauge.removeSensorSet();
            this.rainGauge = null;
            super.removeSensor(3);
        }
        else
            System.out.println("Error!!! This station hasn't a rain gauge to detach yet.");
    }

    public void attachThermometer(Thermometer thermometer) {
        if (this.thermometer == null) {
            this.thermometer = thermometer;
            this.thermometer.setSensorsSet(this);
            this.thermometer.generateValue();
            super.setSensor(this.thermometer.getSensorID(), this.thermometer.getActualValue(), 4);
            this.thermometer.start();
        }
        else
            System.out.println("Error!!! This station already has a thermometer.");
    }

    public void detachThermometer() {
        if (thermometer != null) {
            this.thermometer.interrupt();
            this.thermometer.removeSensorSet();
            this.thermometer = null;
            super.removeSensor(4);
        }
        else
            System.out.println("Error!!! This station hasn't a thermometer to detach yet.");
    }

    public void attachHygrometer(Hygrometer hygrometer) {
        if (this.hygrometer == null) {
            this.hygrometer = hygrometer;
            this.hygrometer.setSensorsSet(this);
            this.hygrometer.generateValue();
            super.setSensor(this.hygrometer.getSensorID(), this.hygrometer.getActualValue(), 5);
            this.hygrometer.start();
        }
        else
            System.out.println("Error!!! This station already has a hygrometer.");
    }

    public void detachHygrometer() {
        if (hygrometer != null){
            this.hygrometer.interrupt();
            this.hygrometer.removeSensorSet();
            this.hygrometer = null;
            super.removeSensor(5);
        }
        else
            System.out.println("Error!!! This station hasn't a hygrometer to detach yet.");
    }

    public int getNumSensors() {
        return numSensors;
    }
}
