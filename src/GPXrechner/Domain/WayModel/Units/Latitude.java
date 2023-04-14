package GPXrechner.Domain.WayModel.Units;

/**
 * A latitude in Degrees on Planet Earth
 */
public class Latitude {
    private final double value;

    public Latitude(double value) {
        if (Math.abs(value) <= 90) {
            this.value = value;
        } else {
            throw new RuntimeException("latitude out of bounds val: " + value + "bounds abs: " + 90);
        }
    }

    public double getValue() {
        return value;
    }
}
