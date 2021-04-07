public class IndoorMeterSet extends SensorsSet {
    private static final int numSensors = 2;
    private Thermometer thermometer;
    private Hygrometer hygrometer;

    public IndoorMeterSet(){
        super(numSensors);
        thermometer = null;
        hygrometer = null;
    }

    public void attachThermometer(Thermometer thermometer) {
        if (this.thermometer == null) {
            this.thermometer = thermometer;
            this.thermometer.setSensorsSet(this);
            super.setSensor(this.thermometer.getSensorID(), this.thermometer.getActualValue(), 0);
            this.thermometer.start();
        }
        else
            System.out.println("Error!!! This indoor meter already has a thermometer.");//TODO: è scritto indoor meter, ma in verità questo set è genereale e riusabile, perciò il tipo prendilo dalla classe IndoorMeter. Correggi anche gli altri.
    }

    public void detachThermometer() {
        if (thermometer != null) {
            this.thermometer.interrupt();
            this.thermometer.removeSensorSet();
            this.thermometer = null;
            super.removeSensor(0);
        }
        else
            System.out.println("Error!!! This station hasn't a indoor meter  to detach yet.");
    }

    public void attachHygrometer(Hygrometer hygrometer) {
        if (this.hygrometer == null) {
            this.hygrometer = hygrometer;
            this.hygrometer.setSensorsSet(this);
            super.setSensor(this.hygrometer.getSensorID(), this.hygrometer.getActualValue(), 1);
            this.hygrometer.start();
        }
        else
            System.out.println("Error!!! This indoor meter already has a hygrometer.");
    }

    public void detachHygrometer() {
        if (hygrometer != null) {
            this.hygrometer.interrupt();
            this.hygrometer.removeSensorSet();
            this.hygrometer = null;
            super.removeSensor(1);
        }
        else
            System.out.println("Error!!! This indoor meter  hasn't a hygrometer to detach yet.");
    }

    public int getNumSensors() {
        return numSensors;
    }
}
