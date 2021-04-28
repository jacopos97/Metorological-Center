package meteorologicalcenter;

import sensors.*;
import transmitters.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MeteorologicalCenter implements Observer {
    private static final String type = "MeteorologicalCenter";
    private static int controlledParameters;
    private static MeteorologicalCenter meteorologicalCenterInstance;
    private MeteorologicalCenterTransmitter meteorologicalCenterTransmitter;
    private ArrayList<SensorsSetTransmitter> observedWeatherStation;
    private ArrayList<Float[]> weatherStationState;
    private Float cityAirPressure;
    private Float cityWindSpeed;
    private Float cityWindDirection;
    private Float cityPrecipitation;
    private Float cityTemperature;
    private Float cityHumidity;

    public static MeteorologicalCenter getInstance() {
        if (meteorologicalCenterInstance == null)
            meteorologicalCenterInstance = new MeteorologicalCenter();
        return meteorologicalCenterInstance;
    }

    private MeteorologicalCenter() {
        controlledParameters = 6;
        observedWeatherStation = new ArrayList<>();
        weatherStationState = new ArrayList<>();
        meteorologicalCenterTransmitter = new MeteorologicalCenterTransmitter();
        meteorologicalCenterTransmitter.startTransmitter();
    }

    public void attachWeatherStation(WeatherStation weatherStation) {
        boolean checkPresence = false;
        int i = 0;
        while (!checkPresence && i < observedWeatherStation.size()) {
            if (SensorsSetID.areEqual(observedWeatherStation.get(i).getSensorsSetId(), weatherStation.getSensorsSetID()))
                checkPresence = true;
            i++;
        }
        if (!checkPresence) {
            observedWeatherStation.add(weatherStation.getSensorsSetTransmitter());
            weatherStation.addObserver(this);
            weatherStationState.add(null);
        }
    }

    public void detachWeatherStation(WeatherStation weatherStation) {
        boolean checkPresence = false;
        int i = 0;
        while (!checkPresence && i < observedWeatherStation.size()) {
            if (SensorsSetID.areEqual(observedWeatherStation.get(i).getSensorsSetId(), weatherStation.getSensorsSetID()))
                checkPresence = true;
            i++;
        }
        if (checkPresence) {
            observedWeatherStation.remove(i - 1);
            weatherStation.deleteObserver(this);
            weatherStationState.remove(i - 1);
        }
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        boolean found = false;
        int i = 0;
        while (i < observedWeatherStation.size() && !found) {
            if (SensorsSetID.areEqual(((SensorsSetTransmitter)o).getSensorsSetId(), observedWeatherStation.get(i).getSensorsSetId()))
                found = true;
            i++;
        }
        if (found) {
            boolean[] changedValues = new boolean[((SensorsSetTransmitter)o).getState().length];
            if (weatherStationState.get(i - 1) == null) {
                weatherStationState.set(i - 1, new Float[((SensorsSetTransmitter)o).getState().length]);
                for (int j = 0; j < weatherStationState.get(i - 1).length; j++) {
                    weatherStationState.get(i - 1)[j] = ((Transmitter) o).getState()[j];
                    changedValues[j] = true;
                }
            } else {
                for (int j = 0; j < weatherStationState.get(i - 1).length; j++) {
                    if (weatherStationState.get(i - 1)[j] != ((Transmitter) o).getState()[j]) {
                        weatherStationState.get(i - 1)[j] = ((Transmitter) o).getState()[j];
                        changedValues[j] = true;
                    }
                }
            }
            //il blocco di commento che segue serve per il controllo
            /*System.out.print(observedWeatherStation.get(i - 1).getSensorsSetId().getId() + ": ");
            for (int j = 0; j < weatherStationState.get(i - 1).length; j++) {
                System.out.print(weatherStationState.get(i - 1)[j] + "      ");
            }
            System.out.println();
            System.out.println();*/

            boolean firstInitialization = firstInitialization();
            updateMeteorologicalParameters(changedValues);
            if (!firstInitialization && firstInitialization())
                notifyAll();

            //la funzione displayParameters() serve a controllare la correttezza del codice
            //displayParameters();
        }
    }

    private void updateMeteorologicalParameters(boolean[] changed) {
        for(int i=0; i<changed.length; i++) {
            if (changed[i]) {
                float sum = 0;
                int j = 0;
                for (Float[] values : weatherStationState) {
                    if (values != null) {
                        sum = sum + values[i];
                        j++;
                    }
                }
                updateParameter(i, sum/j);
            }
        }
    }

    private void updateParameter(int pos, float newValue) {
        String sensorType = WeatherStation.getSensorType(pos);
        if (sensorType != null) {
            if (sensorType.equals(Barometer.getType()))
                cityAirPressure = newValue;
            else if (sensorType.equals(Anemometer.getType()))
                cityWindSpeed = newValue;
            else if (sensorType.equals(WindVane.getType()))
                cityWindDirection = newValue;
            else if (sensorType.equals(RainGauge.getType()))
                cityPrecipitation = newValue;
            else if (sensorType.equals(Thermometer.getType()))
                cityTemperature = newValue;
            else if (sensorType.equals(Hygrometer.getType()))
                cityHumidity = newValue;
        }
        else
            System.out.println("Error!!! The meteorological center doesn't follow this meteorological parameter.");
    }

    public void displayParameters() {
        System.out.print(cityAirPressure + "      ");
        System.out.print(cityWindSpeed + "      ");
        System.out.print(cityWindDirection + "      ");
        System.out.print(cityPrecipitation + "      ");
        System.out.print(cityTemperature + "      ");
        System.out.print(cityHumidity + "      ");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void displayWeatherStationParameters(int pos) {
        if (pos>=0 && pos<observedWeatherStation.size()) {
            System.out.print(observedWeatherStation.get(pos).getSensorsSetId().getType()+"-");
            System.out.print(observedWeatherStation.get(pos).getSensorsSetId().getId()+": ");
            for(int i=0; i<weatherStationState.get(pos).length; i++)
                System.out.print(weatherStationState.get(pos)[i]+"     ");
            System.out.println();
            System.out.println();
        }
    }

    public static int getControlledParameters() {
        return controlledParameters;
    }

    public synchronized float[] getParameters() throws InterruptedException{
        while(!firstInitialization())
            wait();
        float[] parameters = new float[controlledParameters];
        parameters[0] = cityAirPressure;
        parameters[1] = cityWindSpeed;
        parameters[2] = cityWindDirection;
        parameters[3] = cityPrecipitation;
        parameters[4] = cityTemperature;
        parameters[5] = cityHumidity;
        return  parameters;
    }

    public void attachMeteorologicalCenterTransmitter(MeteorologicalCenterTransmitter meteorologicalCenterTransmitter) {
        if (this.meteorologicalCenterTransmitter == null)
            this.meteorologicalCenterTransmitter = meteorologicalCenterTransmitter;
    }

    public void detachMeteoroogicalCenterTransmitter() {
        if(this.meteorologicalCenterTransmitter != null)
            this.meteorologicalCenterTransmitter = null;
    }

    public String getType(){
        return type;
    }

    public void interruptMeteorologicalCenterTransmitter() {
        meteorologicalCenterTransmitter.interruptTransmitter();
    }

    private boolean firstInitialization() {
        return cityAirPressure != null && cityWindSpeed != null && cityWindDirection != null && cityPrecipitation != null && cityTemperature != null && cityHumidity != null;
    }

    public void addObserver(Observer observer) {
        meteorologicalCenterTransmitter.addObserver(observer);
    }

    public void deleteObserver(Observer observer) {
        meteorologicalCenterTransmitter.deleteObserver(observer);
    }
}

