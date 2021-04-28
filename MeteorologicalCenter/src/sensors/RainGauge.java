package sensors;

public class RainGauge extends Sensor {
    private static final String type = "RainGauge";
    private static int numRainGauge = 0;

    public RainGauge() {
        super(7000, numRainGauge, type);
        numRainGauge++;
    }

    void generateValue() {
        super.setActualValue((float) (Math.random() * 26)); //mm
    }

    public static String getType() {
        return type;
    }
}