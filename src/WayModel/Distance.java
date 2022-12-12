package WayModel;

/**
 * Distance in meter
 */
public class Distance {
    private double value;

    public Distance(double value) {
        if (value >= 0) {
            this.value = value;
        }
        else{
            throw new RuntimeException("Distance out of bounds: " + value + "Elevation must be a positive number");
        }
    }

    public double getValue() {
        return value;
    }
}
