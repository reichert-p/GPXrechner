package GPXrechner.Domain.WayModel.Units;

/**
 * Speed in meters per hour
 */
public class Pace {
    private final double value;

    public Pace(double value) {
        if (value < 3600000 && value >= 0) {
            this.value = value;
        } else {
            throw new RuntimeException("Pace out of bounds: " + value + "Pace expected to have a positive Value below 1000000 m/h which equals 1000 km/h");
        }
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + Math.round(value) + "m/s";
    }
}
