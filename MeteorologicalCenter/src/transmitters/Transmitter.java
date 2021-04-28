package transmitters;

import java.util.Observable;

public abstract class Transmitter extends Observable implements Runnable{
    private float[] state;
    private Thread thread;


    Transmitter(int stateLength) {
        state = new float[stateLength];
        thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            while(true)
                updateState(getData());
        }catch(InterruptedException ignored) {
        }
    }

    private void updateState(float[] state) throws InterruptedException {
        for (int i=0; i<this.state.length; i++) {
            if (this.state[i] != (state[i])) {
                this.state[i] = state[i];
                setChanged();
            }
        }
        if (hasChanged())
            transmit();
        else
            Thread.sleep(1);
    }

    public float[] getState() {
        return state;
    }

    public void startTransmitter() {
        thread.start();
    }

    public void interruptTransmitter() {
        thread.interrupt();
    }

    private void transmit(){
        notifyObservers();
    }

    public abstract String getTransmitterType();
    abstract float[] getData() throws InterruptedException;
}
