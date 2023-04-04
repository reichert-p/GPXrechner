package test.Calculations.Toursplitting;

import GPXrechner.Calculations.TourSplitting.TimeHeuristic;
import GPXrechner.WayModel.Location;

import java.time.Duration;

public class timeHeuristicMock implements TimeHeuristic {
    @Override
    public Duration predictTime(Location a, Location b) {
        return Duration.ZERO;
    }
}
