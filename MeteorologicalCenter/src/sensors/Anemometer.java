package sensors;

public class Anemometer extends Sensor {
    private static final String type = "Anemometer";
    private static int numAnemometer = 0;
    private boolean firstTime;

    public Anemometer() {
        super(4000, numAnemometer, type);
        numAnemometer++;
        firstTime = true;
    }

    void generateValue(){
        float value;
        if (firstTime) {
            value = (float) (Math.random() * 150);
            firstTime = false;
        }
        else {
            do
                value = super.getActualValue() + ((float) ((Math.random() - 0.5) * 3));// km/h
            while (value < 0 || value > 150);
        }
        super.setActualValue(value);
    }

    public static String getType() {
        return type;
    }
}