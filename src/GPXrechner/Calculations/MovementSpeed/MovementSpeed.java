package GPXrechner.Calculations.MovementSpeed;

import GPXrechner.WayModel.Units.Pace;

public interface MovementSpeed {
    public Pace getHorizontalSpeed();
    public Pace getClimbingSpeed();
    public Pace getDescendingSpeed();
}
