package sensors;

public class Hygrometer extends Sensor {
    private static final String type = "Hygrometer";
    private static int numHygrometer = 0;
    private boolean firsTime;

    public Hygrometer() {
        super(10000, numHygrometer, type);
        numHygrometer++;
        firsTime = true;
    }

    void generateValue(){
        float value;
        if (firsTime) {
            value = (float)((Math.random()* 100));
            firsTime = false;
        }
        else {
            do
                value = super.getActualValue() + ((float) ((Math.random() - 0.5) * 8));// %
            while (value < 0 || value > 100);
        }
        super.setActualValue(value);
    }

    public static String getType() {
        return type;
    }
}