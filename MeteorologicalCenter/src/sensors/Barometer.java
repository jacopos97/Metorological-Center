package sensors;

public class Barometer extends Sensor {
    private static final String type = "Barometer";
    private static int numBarometer = 0;

    public Barometer() {
        super(6000, numBarometer, type);
        numBarometer++;
    }

    void generateValue(){
        super.setActualValue((float)(Math.random() * 65+ 980)); //ettoPascal
    }

    public static String getType() {
        return type;
    }
}
