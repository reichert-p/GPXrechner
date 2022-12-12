package WayModel;

/**
 * A altitude Difference of a Path
 */
public class AltitudeGain {
    double up;
    double down;

    public AltitudeGain(double up, double down) {
        this.up = up;
        this.down = down;
    }

    public double getUp() {
        return up;
    }

    public double getDown() {
        return down;
    }

    @Override
    public String toString() {
        return "up: " + up + "m down: " + down + "m";
    }
}
