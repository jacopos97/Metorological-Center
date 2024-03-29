public class Thermometer extends Sensor {
    private static int numThermometer = 0;
    private boolean firstTime;

    public Thermometer() {
        super(8000, numThermometer, "Thermometer");
        numThermometer++;
        firstTime = true;
    }

    protected void generateValue(){
        float value;
        if (firstTime) {
            value = (float)((Math.random()*45 - 5));
            firstTime = false;
        }
        else
            value = super.getActualValue() + ((float)((Math.random() - 0.5)* 4));// gradi
        super.setActualValue(value);
    }
}