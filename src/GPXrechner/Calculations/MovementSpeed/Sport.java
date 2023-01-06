package GPXrechner.Calculations.MovementSpeed;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.WayModel.Units.Pace;

public enum Sport implements MovementSpeed {
    HIKING(4000,400,600),
    TRAILRUNNING(15000,800,1200),
    CYCLING(20000,400,3000),
    SPRINTING(35000, 1000, 3000);


    Pace horizontal,climbing,descending;;
    Sport(int horizontal, int climbing, int descending){
        this.horizontal = new Pace(horizontal);
        this.climbing = new Pace(climbing);
        this.descending = new Pace(descending);
    }

    @Override
    public Pace getHorizontalSpeed() {
        return horizontal;
    }

    @Override
    public Pace getClimbingSpeed() {
        return climbing;
    }

    @Override
    public Pace getDescendingSpeed() {
        return descending;
    }

    @Override
    public String toString() {
        return "horizontal speed: " + horizontal.toString() + "\n " +
               "climbing speed:   " + climbing.toString() + "\n " +
               "descending speed: " + descending.toString() +"\n";
    }
}
