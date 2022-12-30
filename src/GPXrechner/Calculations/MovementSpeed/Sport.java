package GPXrechner.Calculations.MovementSpeed;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.WayModel.Units.Pace;

public enum Sport implements MovementSpeed {
    //TODO dies hier pflegen
    HIKING(4000,400,600),
    TRAILRUNNING(15000,800,1200),
    CYCLING(20000,400,3000);


    int a,b,c;
    Sport(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Pace getHorizontalSpeed() {
        return new Pace(a);
    }

    @Override
    public Pace getClimbingSpeed() {
        return new Pace(b);
    }

    @Override
    public Pace getDescendingSpeed() {
        return new Pace(c);
    }
}
