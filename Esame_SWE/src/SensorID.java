public class SensorID {
    private int id;
    private String type;

    public SensorID(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public static boolean areEqual(SensorID a, SensorID b) {
        return ((a.getId() == b.getId()) && (a.getType().equals(b.getType())));
    }
}