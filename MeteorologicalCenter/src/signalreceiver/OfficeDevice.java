package signalreceiver;

import meteorologicalcenter.MeteorologicalCenter;
import sensors.IndoorMeter;
import sensors.WeatherStation;
import transmitters.*;

import java.util.Observable;
import java.util.Observer;

public class OfficeDevice implements Observer {
    private static int numOfficeDevice = 0;
    private int id;
    private int displayTime;
    private SensorsSetTransmitter observedIndoorMeter;
    private long lastUpdate;
    private boolean firstInternalParametersUpdate;
    private boolean firstExternalParametersUpdate;
    private float internalTemperature;
    private float internalHumidity;
    private float externalTemperature;
    private float externalHumidity;

    public OfficeDevice(int displayTime) {
        id = numOfficeDevice;
        this.displayTime = displayTime;
        numOfficeDevice++;
        firstInternalParametersUpdate = true;
        firstExternalParametersUpdate = true;
        MeteorologicalCenter.getInstance().addObserver(this);
    }

    @Override
    public synchronized void update(Observable o, Object arg){
        if(((Transmitter) o).getTransmitterType().equals("IndoorMeter")) {
            updateInternalParameters(((SensorsSetTransmitter)o));
        }
        else if (((Transmitter) o).getTransmitterType().equals("MeteorologicalCenter")) {
            updateExternalParameters((MeteorologicalCenterTransmitter)o);
        }
        if (System.currentTimeMillis()-lastUpdate >= displayTime && !firstExternalParametersUpdate && !firstInternalParametersUpdate){
            // la funzione display che segue serve a controllare la correttezza del codice
            //display();
            lastUpdate = System.currentTimeMillis();
        }

    }

    private void updateInternalParameters(SensorsSetTransmitter sensorsSetTransmitter){
        internalTemperature = sensorsSetTransmitter.getState()[IndoorMeter.getDevicePos("Thermometer")];
        internalHumidity = sensorsSetTransmitter.getState()[IndoorMeter.getDevicePos("Hygrometer")];
        if(firstInternalParametersUpdate)
            firstInternalParametersUpdate = false;
    }

    private void updateExternalParameters(MeteorologicalCenterTransmitter meteorologicalCenterTransmitter) {
        externalTemperature = meteorologicalCenterTransmitter.getState()[WeatherStation.getDevicePos("Thermometer")];
        externalHumidity = meteorologicalCenterTransmitter.getState()[WeatherStation.getDevicePos("Hygrometer")];
        if(firstExternalParametersUpdate)
            firstExternalParametersUpdate = false;
    }

    private void display() {
        System.out.println("OfficeDevice-"+id);
        System.out.println("Interno:  "+internalTemperature+"     "+internalHumidity);
        System.out.println("Esterno:  "+externalTemperature+"     "+externalHumidity);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void attachIndoorMeter(IndoorMeter indoorMeter) {
        if (observedIndoorMeter==null){
            observedIndoorMeter = indoorMeter.getSensorsSetTransmitter();
            indoorMeter.addObserver(this);
        }
        else
            System.out.println("Error!!! The officeDevice already has an indoorMeter");
    }

    public void detachIndoorMeter() {
        if (observedIndoorMeter!=null) {
            observedIndoorMeter.deleteObserver(this);
            observedIndoorMeter = null;
        }
        else
            System.out.println("Error!!! The officeDevice already has an indoorMeter");
    }
}

