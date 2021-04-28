package sensors;

public class WindVane extends Sensor {
    private static final String type = "WindVane";
    private static int numWindVane = 0;
    private boolean firstTime;

    public WindVane() {
        super(4000, numWindVane, type);
        numWindVane++;
        firstTime = true;
    }

    void generateValue(){
        float value;
        if(firstTime) {
            value = (float)(Math.random() * 360);
            firstTime = false;
        }
        else {
            value = super.getActualValue() + ((float) ((Math.random() - 0.5) * 6));//gradi
            if (value < 0)
                value = value + 360;
            else if (value > 360)
                value = value + 360;
        }
        super.setActualValue(value);
    }
    public static String getType() {
        return type;
    }
}
