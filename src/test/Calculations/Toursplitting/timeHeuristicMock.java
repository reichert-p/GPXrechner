package test.Calculations.Toursplitting;

import GPXrechner.Domain.Calculations.TourSplitting.TimeHeuristic;
import GPXrechner.Domain.WayModel.WayModel.Location;

import java.time.Duration;

public class timeHeuristicMock implements TimeHeuristic {
    @Override
    public Duration predictTime(Location a, Location b) {
        return Duration.ZERO;
    }
}
