import java.util.Arrays;

public abstract  class SensorsSet {
    private SensorID[] sensorID;
    private float[] sensorValue;
    private boolean full;


    protected SensorsSet(int sensorsNum) {
        sensorID = new SensorID[sensorsNum];
        sensorValue = new float[sensorsNum];
        full = false;
    }

    public synchronized void setValue(SensorID sensorID, float value) throws InterruptedException {
        while (!full)
            wait();
        int i = 0;
        boolean found = false;
        while (!found && i < this.sensorID.length) {
            if (SensorID.areEqual(this.sensorID[i], sensorID)) {
                found = true;
                sensorValue[i] = value;
            }
            i++;
        }
    }

    public synchronized float[] getSensorsValue() throws InterruptedException {
        while (!full)
            wait();
        return Arrays.copyOf(sensorValue, sensorValue.length);
    }

    protected synchronized void setSensor(SensorID sensorID, float value, int pos) {
        boolean temp = isFull();
        this.sensorID[pos] = sensorID;
        this.sensorValue[pos] = value;
        full = isFull();
        if (full && !temp) {
            notifyAll();
        }
    }

    protected synchronized void removeSensor(int pos) {
        //sensors[pos] = null;
        sensorID[pos] = null;
        sensorValue[pos] = 0;
        full = false;
    }

    private boolean isFull() {
        boolean check = true;
        int i = 0;
        while (i < sensorID.length && check) {
            if (sensorID[i] == null)
                check = false;
            i++;
        }
        return check;
    }
}
