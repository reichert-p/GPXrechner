package GPXrechner.WayModel.Units;

/**
 * Distance in meter
 */
public class Distance {
    private double value;

    @Override
    public String toString() {
        return "Distance: " + (int)value + " meters";
    }

    public Distance(double value) {
        if (value >= 0) {
            this.value = value;
        }
        else{
            throw new RuntimeException("Distance out of bounds: " + value + "Elevation must be a positive number");
        }
    }

    public void addDistance(Distance difference){
        value += difference.getValue();
    }

    public double getValue() {
        return value;
    }
}
