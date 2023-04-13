package GPXrechner.Domain.Calculations.TourSplitting;

import GPXrechner.Domain.WayModel.WayModel.Location;

import java.time.Duration;

public interface TimeHeuristic {
    Duration predictTime(Location a, Location b);
}
