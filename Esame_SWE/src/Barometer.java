public class Barometer extends Sensor {
    private static int numBarometer = 0;

    public Barometer() {
        super(6000, numBarometer, "Barometer");
        numBarometer++;
    }

    protected void generateValue(){
        super.setActualValue((float)(Math.random() * 65+ 980)); //ettoPascal
    }
}
