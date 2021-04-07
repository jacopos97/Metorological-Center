public class IndoorMeter extends Repeater {
    //private static final String type = "IndoorMeter";
    private static int numIndoorMeter = 0;
    private int id;

    public IndoorMeter() {
        this.id = numIndoorMeter;
        numIndoorMeter++;
        super.setSensorsSet(new IndoorMeterSet());
        super.initialize(((IndoorMeterSet)super.getSensorsSet()).getNumSensors());
    }

    public void transmit() {
        //notifyObservers();
    }

    public int getId() {
        return id;
    }

    public void attachHygrometer(Hygrometer h) {
        ((IndoorMeterSet)super.getSensorsSet()).attachHygrometer(h);
    }

    public void attachThermometer(Thermometer t) {
        ((IndoorMeterSet)super.getSensorsSet()).attachThermometer(t);
    }
}
