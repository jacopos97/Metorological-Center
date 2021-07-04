package sensors;

import java.util.Arrays;
import java.util.Observer;

import transmitters.SensorsSetTransmitter;

public abstract class SensorsSet {
    private final int numSensors;
    private SensorsSetID sensorsSetID;
    private float[] sensorsValue;
    private boolean full;
    private SensorsSetTransmitter sensorsSetTransmitter;

    SensorsSet(int numSensors, int numSensorsSet, String type) {
        this.numSensors = numSensors;
        sensorsSetID = new SensorsSetID(numSensorsSet, type);
        sensorsValue = new float[this.numSensors];
        full = false;
        sensorsSetTransmitter = new SensorsSetTransmitter(numSensors);
        sensorsSetTransmitter.setSensorsSet(this);
        sensorsSetTransmitter.startTransmitter();
    }

    synchronized void setValue(SensorID sensorID, float value) throws InterruptedException {
        while (!full)
            wait();
        int pos = getSensorPos(sensorID);
        if (pos >= 0 && pos < sensorsValue.length){
            sensorsValue[pos] = value;
        }
        else
            System.out.println("Error!!! An external sensor wants to update the "+sensorsSetID.getType());
    }

    public synchronized float[] getSensorsValue() throws InterruptedException {
        while (!full)
            wait();
        return Arrays.copyOf(sensorsValue, sensorsValue.length);
    }

    synchronized void setSensor(float value, int pos) {
        boolean temp = full;
        this.sensorsValue[pos] = value;
        full = isFull();
        if (full && !temp) {
            notifyAll();
        }
    }

    synchronized void removeSensor(int pos) {
        sensorsValue[pos] = 0;
        full = false;
    }

    public SensorsSetTransmitter getSensorsSetTransmitter() {
        return  sensorsSetTransmitter;
    }

    public void attachSensorsSetTransmitter(SensorsSetTransmitter sensorsSetTransmitter) {
        if (this.sensorsSetTransmitter == null){
            if (sensorsSetTransmitter.getSensorsSet() == null) {
                if (sensorsSetTransmitter.getState().length == getNumSensors()){
                    this.sensorsSetTransmitter = sensorsSetTransmitter;
                    sensorsSetTransmitter.setSensorsSet(this);
                    sensorsSetTransmitter.startTransmitter();
                }
                else
                    System.out.println("Error!!! The transmitter doesn't match with this SensorSet");
            }
            else if (sensorsSetTransmitter.getSensorsSet() == this){
                this.sensorsSetTransmitter = sensorsSetTransmitter;
                sensorsSetTransmitter.startTransmitter();
            }
            else
                System.out.println("Error!!! The transmitter is already associated to a SensorSet");
        }
        else
            System.out.println("Error!!! This "+sensorsSetID.getType()+" already has a transmitter");
    }

    public void detachSensorsSetTransmitter() {
        if (this.sensorsSetTransmitter != null) {
            SensorsSetTransmitter temp = sensorsSetTransmitter;
            sensorsSetTransmitter = null;
            if (temp.getSensorsSet() != null)
                temp.removeSensorsSet();
        }
    }

    public SensorsSetID getSensorsSetID() {
        return  sensorsSetID;
    }

    public void interruptSensorsSetTransmitter() {
        if (this.sensorsSetTransmitter != null)
            this.sensorsSetTransmitter.interruptTransmitter();
    }

    public int getNumSensors() {
        return numSensors;
    }

    public void addObserver(Observer observer) {
        getSensorsSetTransmitter().addObserver(observer);
    }

    public void deleteObserver(Observer observer) {
        getSensorsSetTransmitter().deleteObserver(observer);
    }

    public abstract void interruptSensorsSet();
    abstract int getSensorPos(SensorID sensorID);
    abstract boolean isFull();
}
