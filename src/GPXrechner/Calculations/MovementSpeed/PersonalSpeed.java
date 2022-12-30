package GPXrechner.Calculations.MovementSpeed;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.WayModel.Units.Pace;

public class PersonalSpeed implements MovementSpeed {
    Pace a,b,c;

    //TODO probably a refactor here

    public PersonalSpeed(Pace a, Pace b, Pace c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Pace getHorizontalSpeed() {
        return a;
    }

    @Override
    public Pace getClimbingSpeed() {
        return b;
    }

    @Override
    public Pace getDescendingSpeed() {
        return c;
    }
}
