package enumeration;

public enum VehicleType {
    CAR("car");

    String abbr;

    VehicleType(String abbr) {
        this.abbr = abbr;
    }

    public String getAbbr() {
        return abbr;
    }
}
