package GPXrechner.Calculations.TourSplitting;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.TimePrediction;
import GPXrechner.WayModel.Location;

import java.time.Duration;

public class DirectWayHeuristic implements TimeHeuristic {
    @Override
    public Duration predictTime(Location a, Location b, MovementSpeed movementSpeed) {
        return TimePrediction.predictTime(a,b,movementSpeed);
    }
}
