package GPXrechner.Domain.Calculations.MovementSpeed;

import GPXrechner.Domain.WayModel.WayModel.Units.Pace;

public interface MovementSpeed {
    Pace getHorizontalSpeed();

    Pace getClimbingSpeed();

    Pace getDescendingSpeed();
}
