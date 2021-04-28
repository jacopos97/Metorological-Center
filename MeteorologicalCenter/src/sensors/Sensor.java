package sensors;

abstract class Sensor implements Runnable{
    private SensorID sensorID;
    private SensorsSet sensorsSet;
    private Thread thread;
    private float actualValue;
    private int acquisitionTime;

    public void run() {
        try{
            while(true){
                if (sensorsSet != null)
                    sensorsSet.setValue(getSensorID(), actualValue);
                Thread.sleep(acquisitionTime);
                generateValue();
            }
        }catch(InterruptedException ignored) {}
    }


    Sensor(int acquisitionTime, int id, String type){
        this.sensorID = new SensorID(id, type);
        this.sensorsSet = null;
        this.acquisitionTime = acquisitionTime;
        thread = new Thread(this);
    }

    void setSensorsSet(SensorsSet sensorsSet) {
        this.sensorsSet = sensorsSet;
    }

    void removeSensorSet() {
        this.sensorsSet = null;
    }

    SensorID getSensorID() {
        return sensorID;
    }

    float getActualValue(){
        return actualValue;
    }

    void setActualValue(float value) {
        this.actualValue = value;
    }

    void startSensor() {
        generateValue();
        thread.start();
    }

    void interruptSensor() {
        thread.interrupt();
    }

    abstract void generateValue();
}
