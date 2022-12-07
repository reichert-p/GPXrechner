package WayModel;

/**
 * Beschreibt eine orthogonale Höhe über einem Meeresspiegel
 */
public class Elevation {
    private double value;

    public Elevation(double value) {
        if (value < 9000 && value > -500) {
            this.value = value;
        }
        else{
            throw new RuntimeException("Elevation out of bounds: " + value + "Elevation expected to be between -500 and 9000: " + 90);
        }
    }

    public double getValue() {
        return value;
    }

}
