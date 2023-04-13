package GPXrechner.Domain.WayModel.WayModel.Units;

/**
 * A longitude in degrees on Planet Earth
 */
public class Longitude {
    private final double value;

    public Longitude(double value) {
        if (Math.abs(value) <= 180) {
            this.value = value;
        } else {
            throw new RuntimeException("longitude out of bounds val: " + value + "bounds abs: " + 180);
        }
    }

    public double getValue() {
        return value;
    }

}
