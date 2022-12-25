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

    public AltitudeGain(double difference){ //returns an Altitude Gain which only moves one vertical direction
        if (difference > 0){
            new AltitudeGain(difference, 0);
        }else {
            new AltitudeGain(0, Math.abs(difference));
        }
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

    public void addAltitude(AltitudeGain diff) {
        this.up += diff.up;
        this.down += diff.down;
    }

    public double getManhattenNorm(){
        return Math.abs(up) + Math.abs(down);
    }
}
