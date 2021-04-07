public class RainGauge extends Sensor {
    private static int numRainGauge = 0;

    public RainGauge() {
        super(7000, numRainGauge, "RainGauge");
        numRainGauge++;
    }

    protected void generateValue() {
        super.setActualValue((float) (Math.random() * 26)); //mm
    }
}