package GPXrechner.WayModel.Units;

/**
 * orthogonal Distance to sea level in meter
 */
public class Elevation {
    private double value;

    public Elevation(double value) {
        if (value < 9000 && value > -500) {
            this.value = value;
        }
        else{
            throw new RuntimeException("Elevation out of bounds: " + "Elevation expected to be between -500 and 9000: " + value);
        }
    }

    public double getValue() {
        return value;
    }

}
