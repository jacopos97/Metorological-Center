package transmitters;

import sensors.SensorsSet;
import sensors.SensorsSetID;

import java.util.Observer;

public class SensorsSetTransmitter extends Transmitter {
    private SensorsSet sensorsSet;

    public SensorsSetTransmitter(int stateLength) {
        super(stateLength);
    }

    public SensorsSetID getSensorsSetId() {
        return sensorsSet.getSensorsSetID();
    }

    public String getTransmitterType() {
        return sensorsSet.getSensorsSetID().getType();
    }

    float[] getData() throws InterruptedException{
        if (sensorsSet == null)
            return getState();
        else
            return sensorsSet.getSensorsValue();
    }

    public boolean setSensorsSet(SensorsSet sensorsSet) {
        boolean temp = false;
        if (this.sensorsSet == null) {
            if (sensorsSet.getNumSensors() == getState().length) {
                this.sensorsSet = sensorsSet;
                temp = true;
            }
            else
                System.out.println("Error!!! The SensorsSet doesn't match with this transmitter");
        }
        else
            System.out.println("Error!!! This SensorsSetTransmitter is already associated to a SensorSet");
        return temp;
    }

    @Override
    public void addObserver(Observer observer) {
        if (sensorsSet.getSensorsSetID().getType().equals("IndoorMeter")){
            if (countObservers() == 0)
                super.addObserver(observer);
            else
                System.out.println("This IndoorMeter already has an Observer");}
        else
            super.addObserver(observer);
    }

    public void removeSensorsSet() {
        interruptTransmitter();
        this.sensorsSet = null;
    }
}
