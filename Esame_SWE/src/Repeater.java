import java.util.Observable;

public abstract class Repeater extends Observable implements Runnable{
    private float[] state;
    private SensorsSet sensorsSet;
    private Thread thread;//TODO: guarda se va bene


    public void initialize(int length) {
        state = new float[length];
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            while(true) {
                updateState(getSensorsValue());
            }
        }catch(InterruptedException ignored) {}
    }

    public void updateState(float[] state) throws InterruptedException {
         for (int i=0; i<this.state.length; i++) {
             if (this.state[i] != state[i]) {
                 this.state[i] = state[i];
                 setChanged();
             }
         }
        if (hasChanged())
            transmit();
        else
            Thread.sleep(1);//ricorda che questo serve per la priorità del thread
    }

    public float[] getState() {
        return state;
    }

    public float[] getSensorsValue() throws  InterruptedException{
        return sensorsSet.getSensorsValue();
    }

    public void setSensorsSet(SensorsSet sensorsSet) {
        this.sensorsSet = sensorsSet;
    }

    public SensorsSet getSensorsSet() {
        return this.sensorsSet;
    }

    public void stopStation() {
        thread.interrupt();
    }

    public abstract void transmit();//TODO: implementa transmit nelle classi derivate, controlla dopo se le implementazioni sono uguali, e se cosi fosse, sposta qui l implementazione
}
