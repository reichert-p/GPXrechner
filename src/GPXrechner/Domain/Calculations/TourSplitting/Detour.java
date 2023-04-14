package GPXrechner.Domain.Calculations.TourSplitting;

import GPXrechner.Domain.WayModel.Entities.Path;
import GPXrechner.Domain.WayModel.Location;
import GPXrechner.Domain.WayModel.WayPoint;

import java.time.Duration;
import java.util.ArrayList;

public class Detour {
    int position;
    WayPoint destination;
    Duration expenditure;

    public Detour(int position, WayPoint destination, Duration expenditure) {
        this.position = position;
        this.destination = destination;
        this.expenditure = expenditure;
    }

    /**
     * looks through the whole Path to determine the best place to start Detour as well as the time expenditure
     */
    public static Detour createDetour(Path directPath, WayPoint detourDestination, TimeHeuristic heuristicTimePrediction) {
        ArrayList<Location> pathLocations = directPath.getOrderedLocations();
        if (directPath == null) {
            return null;
        }
        int bestIndex = 0;
        Duration bestIndexDuration = heuristicTimePrediction.predictTime(pathLocations.get(0), detourDestination);
        for (int i = 1; i < directPath.getOrderedLocations().size(); i++) {
            Duration durationFromPathLocation = heuristicTimePrediction.predictTime(pathLocations.get(i), detourDestination);
            if (durationFromPathLocation.compareTo(bestIndexDuration) < 0) {
                bestIndexDuration = durationFromPathLocation;
                bestIndex = i;
            }
        }
        return new Detour(bestIndex, detourDestination, bestIndexDuration);
    }

    public int getPosition() {
        return position;
    }

    public WayPoint getDestination() {
        return destination;
    }

    public Duration getExpenditure() {
        return expenditure;
    }
}