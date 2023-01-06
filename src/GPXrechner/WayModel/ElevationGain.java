package GPXrechner.WayModel;

/**
 * The elevation Difference of a Path
 */
public class ElevationGain {
    double up;
    double down;

    public ElevationGain(double up, double down) {
        this.up = up;
        this.down = down;
    }

    public ElevationGain(double difference){ //returns an Elevation Gain which only moves one vertical direction
        this.up = Math.max(0, difference);
        this.down = Math.abs(Math.min(0,difference));
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

    public void addElevationGain(ElevationGain diff) {
        this.up += diff.up;
        this.down += diff.down;
    }

    public double getManhattenNorm(){
        return Math.abs(up) + Math.abs(down);
    }
}
