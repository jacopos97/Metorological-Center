public abstract class Sensor extends Thread {//TODO: controlla se non è meglio implementare Runnable
    private SensorID sensorID;
    private SensorsSet sensorsSet;
    private float actualValue;
    private int acquisitionTime;

    public void run() {
        try{
            while(true){
                sensorsSet.setValue(getSensorID(), actualValue);
                sleep(acquisitionTime);
                generateValue();
            }
        }catch(InterruptedException ignored) {}
    }


    protected Sensor(int acquisitionTime, int id, String type){
        this.sensorID = new SensorID(id, type);
        this.sensorsSet = null;
        this.acquisitionTime = acquisitionTime;
    }

    protected void setSensorsSet(SensorsSet sensorsSet) {
        this.sensorsSet = sensorsSet;
    }

    protected void removeSensorSet() {
        this.sensorsSet = null;
    }

    protected SensorID getSensorID() {
        return sensorID;
    }

    public float getActualValue(){
        return actualValue;
    }

    public void setActualValue(float value) {
        this.actualValue = value;
    }

    protected abstract void generateValue();
}
