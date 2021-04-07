public class Hygrometer extends Sensor {
    private static int numHygrometer = 0;
    private boolean firsTime;

    public Hygrometer() {
        super(10000, numHygrometer, "Hygrometer");
        numHygrometer++;
        firsTime = true;
    }

    protected void generateValue(){
        float value;
        if (firsTime) {
            value = (float)((Math.random()* 100));
            firsTime = false;
        }
        else {
            do
                value = super.getActualValue() + ((float) ((Math.random() - 0.5) * 8));// km/h
            while (value < 0 || value > 100);
        }
        super.setActualValue(value);
    }
}