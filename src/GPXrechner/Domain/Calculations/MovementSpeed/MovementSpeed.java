package GPXrechner.Domain.Calculations.MovementSpeed;

import GPXrechner.Domain.WayModel.Units.Pace;

public interface MovementSpeed {
    Pace getHorizontalSpeed();

    Pace getClimbingSpeed();

    Pace getDescendingSpeed();
}
