package sensors;

public class IndoorMeter extends SensorsSet {
    private static final String type = "IndoorMeter";
    private static final int thermometerPos = 0;
    private static final int hygrometerPos = 1;
    private static final int maxDevice = 2;
    private static int numIndoorMeter = 0;
    private Thermometer thermometer;
    private Hygrometer hygrometer;

    public IndoorMeter(){
        super(maxDevice, numIndoorMeter, type);
        numIndoorMeter++;
        thermometer = null;
        hygrometer = null;
    }

    public void attachThermometer(Thermometer thermometer) {
        if (this.thermometer == null) {
            this.thermometer = thermometer;
            this.thermometer.setSensorsSet(this);
            super.setSensor(this.thermometer.getActualValue(), thermometerPos);
            this.thermometer.startSensor();
        }
        else
            System.out.println("Error!!! This "+type+" meter already has a thermometer.");
    }

    public void detachThermometer() {
        if (thermometer != null) {
            this.thermometer.interruptSensor();
            this.thermometer.removeSensorSet();
            this.thermometer = null;
            super.removeSensor(thermometerPos);
        }
        else
            System.out.println("Error!!! This "+type+" hasn't a indoor meter  to detach yet.");
    }

    public void attachHygrometer(Hygrometer hygrometer) {
        if (this.hygrometer == null) {
            this.hygrometer = hygrometer;
            this.hygrometer.setSensorsSet(this);
            super.setSensor(this.hygrometer.getActualValue(), hygrometerPos);
            this.hygrometer.startSensor();
        }
        else
            System.out.println("Error!!! This "+type+" meter already has a hygrometer.");
    }

    public void detachHygrometer() {
        if (hygrometer != null) {
            this.hygrometer.interruptSensor();
            this.hygrometer.removeSensorSet();
            this.hygrometer = null;
            super.removeSensor(hygrometerPos);
        }
        else
            System.out.println("Error!!! This "+type+" meter  hasn't a hygrometer to detach yet.");
    }

    public void interruptSensorsSet() {
        thermometer.interruptSensor();
        hygrometer.interruptSensor();
    }

    int getSensorPos(SensorID sensorID) {
        if (SensorID.areEqual(sensorID, thermometer.getSensorID()))
            return thermometerPos;
        else if (SensorID.areEqual(sensorID, hygrometer.getSensorID()))
            return hygrometerPos;
        return super.getNumSensors();
    }

    boolean isFull() {
        boolean full = false;
        if (thermometer!=null && hygrometer!=null)
            full = true;
        return full;
    }

    public static int getDevicePos(String deviceType) {
        if (deviceType.equals("Thermometer"))
            return thermometerPos;
        else if (deviceType.equals("Hygrometer"))
            return hygrometerPos;
        else
            return maxDevice;
    }

    public Thermometer getThermometer() {
        return thermometer;
    }

    public Hygrometer getHygrometer() {
        return hygrometer;
    }
}
