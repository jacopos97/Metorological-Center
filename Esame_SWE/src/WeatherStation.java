public class WeatherStation extends Repeater {
    //private static final String type = "WeatherStation";
    private static int numWeatherStation = 0;
    private int id;

    public WeatherStation() {
        this.id = numWeatherStation;
        numWeatherStation++;
        super.setSensorsSet(new WeatherStationSet());
        super.initialize(((WeatherStationSet)super.getSensorsSet()).getNumSensors());
    }

    public void transmit() {
        notifyObservers();
        //il blocco commento che segue serve per controllo
        /*
        float[] values = super.getState();
        System.out.print(getId()+": ");
        for (int i=0; i<values.length; i++) {
            System.out.print(values[i]+"      ");
        }
        System.out.println();
        System.out.println();
         */
    }

    public int getId() {
        return id;
    }

    public void attachAnemometer(Anemometer a) {
        ((WeatherStationSet)super.getSensorsSet()).attachAnemometer(a);
    }//TODO: guarda se si pùò evitare questo cast molto brutto, forse lo si può fare mandando un sensore e facendo il controllo nel SensorSet.Prova a farlo così e a quel punto cerca di capire se ti sei liberato del vincolo che una weatherStation deve avere unweatherState.

    public void attachBarometer(Barometer b) {
        ((WeatherStationSet)super.getSensorsSet()).attachBarometer(b);
    }

    public void attachHygrometer(Hygrometer h) {
        ((WeatherStationSet)super.getSensorsSet()).attachHygrometer(h);
    }

    public  void attachRainGauge(RainGauge r) {
        ((WeatherStationSet)super.getSensorsSet()).attachRainGauge(r);
    }

    public void attachThermometer(Thermometer t){((WeatherStationSet)super.getSensorsSet()).attachThermometer(t);
    }

    public void attachWindVane(WindVane w) {
        ((WeatherStationSet)super.getSensorsSet()).attachWindVane(w);
    }
}


//TODO: controlla in tutti i metodi del progetto se è necessario @override e ricontrolla tutte le loro visibilità