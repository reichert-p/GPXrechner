package GPXrechner.Calculations.TourSplitting;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TimePrediction;
import GPXrechner.WayModel.Location;

import java.time.Duration;

public interface TimeHeuristic {
    Duration predictTime(Location a, Location b, MovementSpeed movementSpeed);
}
