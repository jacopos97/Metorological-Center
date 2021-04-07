import java.util.ArrayList;
//import java.util.Observable;
import java.util.Observable;
import java.util.Observer;

public class MeteorologicalCenter implements Observer {//TODO:controlla se è un buon Singleton
    private static MeteorologicalCenter meteorologicalCenterInstance;
    private ArrayList<WeatherStation> observedWeatherStation;
    private ArrayList<Float[]> weatherStationState;
    private float cityAirPressure;
    private float cityWindSpeed;
    private float cityWindDirection;
    private float cityPrecipitation;
    private float cityTemperature;
    private float cityHumidity;

    public static MeteorologicalCenter getInstance() {
        if (meteorologicalCenterInstance == null)
            meteorologicalCenterInstance = new MeteorologicalCenter();
        return meteorologicalCenterInstance;
    }

    protected MeteorologicalCenter() {//se gli cambi visibilità controlla che quella nuova vada bene per il GOF
        observedWeatherStation = new ArrayList<WeatherStation>();
        weatherStationState = new ArrayList<Float[]>();
    }

    public void attachWeatherStation(WeatherStation weatherStation) {
        boolean checkPresence = false;
        int i = 0;
        while (!checkPresence && i < observedWeatherStation.size()) {
            if (observedWeatherStation.get(i) == weatherStation)
                checkPresence = true;
            i++;
        }
        if (!checkPresence) {
            observedWeatherStation.add(weatherStation);
            weatherStation.addObserver(this);
            //weatherStationState.add(new Float[6]);
            weatherStationState.add(null);
        }
    }

    public void detachWeatherStation(WeatherStation weatherStation) {
        boolean checkPresence = false;
        int i = 0;
        while (!checkPresence && i < observedWeatherStation.size()) {
            if (observedWeatherStation.get(i) == weatherStation)
                checkPresence = true;
            i++;
        }
        if (checkPresence) {
            observedWeatherStation.remove(i - 1);
            //weatherStation.removeObserver(this);
            weatherStation.deleteObserver(this);
            weatherStationState.remove(i - 1);
        }
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        boolean found = false;
        int i = 0;
        while (i < observedWeatherStation.size() && !found) {
            if (((WeatherStation) o).getId() == observedWeatherStation.get(i).getId())
                found = true;
            i++;
        }
        if (found) {
            boolean[] changedValues = new boolean[6];
            if (weatherStationState.get(i - 1) == null) {
                weatherStationState.set(i-1, new Float[6]);
                for (int j = 0; j < weatherStationState.get(i - 1).length; j++){
                    weatherStationState.get(i - 1)[j] = ((WeatherStation) o).getState()[j];
                    changedValues[j] = true;
                }
            }
            else {
                for (int j = 0; j < weatherStationState.get(i - 1).length; j++) {
                    if (weatherStationState.get(i - 1)[j] != ((WeatherStation) o).getState()[j]) {
                        weatherStationState.get(i - 1)[j] = ((WeatherStation) o).getState()[j];
                        changedValues[j] = true;
                    }
                }
            }
            //il blocco di commento che segue serve per il controllo
            System.out.print(observedWeatherStation.get(i-1).getId()+": ");
            for (int j=0; j<weatherStationState.get(i - 1).length; j++) {
                System.out.print(weatherStationState.get(i - 1)[j]+"      ");
            }
            System.out.println();
            System.out.println();

            updateMeteorologicalParameter(changedValues);

            System.out.print(cityAirPressure+"      ");
            System.out.print(cityWindSpeed+"      ");
            System.out.print(cityWindDirection+"      ");
            System.out.print(cityPrecipitation+"      ");
            System.out.print(cityTemperature+"      ");
            System.out.print(cityHumidity+"      ");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    private void updateMeteorologicalParameter(boolean[] changed) {
        if (changed[0]){
            float sum = 0;
            int j = 0;
            for (Float[] values : weatherStationState) {
                if (values != null) {
                    sum = sum + values[0];
                    j++;
                }
            }
            cityAirPressure = sum / j;
        }
        if (changed[1]){
            float sum = 0;
            int j = 0;
            for (Float[] values : weatherStationState) {
                if (values != null) {
                    sum = sum + values[1];
                    j++;
                }
            }
            cityWindSpeed = sum / j;
        }
        if (changed[2]) {
            float sum = 0;
            int j = 0;
            for (Float[] values : weatherStationState){
                if (values != null) {
                    sum = sum + values[2];
                    j++;
                }
            }
            cityWindDirection = sum / j;
        }
        if (changed[3]){
            float sum = 0;
            int j = 0;
            for (Float[] values : weatherStationState) {
                if (values != null) {
                    sum = sum + values[3];
                    j++;
                }
            }
            cityPrecipitation = sum / j;
        }
        if (changed[4]){
            float sum = 0;
            int j = 0;
            for (Float[] values : weatherStationState) {
                if (values != null) {
                    sum = sum + values[4];
                    j++;
                }
            }
            cityTemperature = sum / j;
        }
        if (changed[5]){
            float sum = 0;
            int j = 0;
            for (Float[] values : weatherStationState) {
                if (values != null) {
                    sum = sum + values[5];
                    j++;
                }
            }
            cityHumidity = sum / j;
        }
    }

    /*
    public boolean checkFirstStationUpdate(int pos) {
        boolean first = true;
        for (int i=0; i<weatherStationState.get(pos).length; i++) {
            if (weatherStationState.get(pos)[i] != null)
                first = false;
        }
        return first;
    }
     */
}

