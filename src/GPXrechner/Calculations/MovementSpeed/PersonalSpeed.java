package GPXrechner.Calculations.MovementSpeed;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.WayModel.Units.Pace;

public class PersonalSpeed implements MovementSpeed {
    Pace horizontal,climbing,descending;

    public PersonalSpeed(Pace horizontalSpeed, Pace climbingSpeed, Pace descendingSpeed) {
        this.horizontal = horizontalSpeed;
        this.climbing = climbingSpeed;
        this.descending = descendingSpeed;
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
        return "horizontal speed: " + horizontal + "\n " +
                "climbing speed:   " + climbing + "\n " +
                "descending speed: " + descending +"\n";
    }
}
