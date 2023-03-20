package GPXrechner.WayModel.Units;

/**
 * Distance in meter
 */
public final class Distance {
    final private double value;

    @Override
    public String toString() {
        return (int)value + " meters";
    }

    public Distance(double value) {
        if (value >= 0) {
            this.value = value;
        }
        else{
            throw new RuntimeException("Distance out of bounds: " + value + "Distance must be a positive number");
        }
    }

    public Distance addDistance(Distance difference){
        return new Distance(this.value + difference.getValue());
    }

    public double getValue() {
        return value;
    }
}
