package transmitters;

import meteorologicalcenter.MeteorologicalCenter;

public class MeteorologicalCenterTransmitter extends Transmitter {

    public MeteorologicalCenterTransmitter() {
        super(MeteorologicalCenter.getControlledParameters());
    }

    public String getTransmitterType() {
        return MeteorologicalCenter.getInstance().getType();
    }

    float[] getData() throws  InterruptedException{
        return  MeteorologicalCenter.getInstance().getParameters();
    }
}
