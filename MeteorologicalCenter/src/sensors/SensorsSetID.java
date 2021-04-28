package sensors;

public class SensorsSetID {
    private int id;
    private String type;

    SensorsSetID(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public static boolean areEqual(SensorsSetID a, SensorsSetID b) {
        return ((a.getId() == b.getId()) && (a.getType().equals(b.getType())));
    }
}
