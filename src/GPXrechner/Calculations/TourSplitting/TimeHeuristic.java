package GPXrechner.Calculations.TourSplitting;

import GPXrechner.WayModel.Location;

import java.time.Duration;

public interface TimeHeuristic {
    Duration predictTime(Location a, Location b);
}
