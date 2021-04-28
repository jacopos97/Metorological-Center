package sensors;

class SensorID {
    private int id;
    private String type;

    SensorID(int id, String type) {
        this.id = id;
        this.type = type;
    }

    int getId() {
        return this.id;
    }

    String getType() {
        return this.type;
    }

    static boolean areEqual(SensorID a, SensorID b) {
        return ((a.getId() == b.getId()) && (a.getType().equals(b.getType())));
    }
}