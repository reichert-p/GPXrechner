package GPXrechner.Calculations.TourSplitting;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.TimePrediction;
import GPXrechner.WayModel.Location;

import java.time.Duration;

public class DirectWayHeuristic implements TimeHeuristic {
    /*
    This Class's purpose is to deliver the simplest possible implementation of a Heuristic to predict the time between places
    */
    MovementSpeed movementSpeed;
    public DirectWayHeuristic(MovementSpeed movementSpeed){
        this.movementSpeed =movementSpeed;
    }

    @Override
    public Duration predictTime(Location a, Location b) {
        return TimePrediction.predictTime(a,b,movementSpeed);
    }
}
