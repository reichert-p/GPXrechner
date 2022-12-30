package GPXrechner.Calculations;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.PersonalSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Entities.Tour;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Units.Distance;
import GPXrechner.WayModel.Units.Pace;
import GPXrechner.WayModel.TourPoint;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class SpeedCalculator {

    public static Pace predictSpeed(Location a, Location b, MovementSpeed speed){
        TimePrediction timePrediction = new TimePrediction(speed);
        long timeInHours = timePrediction.predictTime(a,b).toHours();
        double distance = DistanceCalculator.calc3dDistance(a,b).getValue();
        return new Pace(distance / timeInHours);
    }

    private static MovementSpeed predictPersonalMovementSpeed (Tour tour){
        List<TourPoint> tourPoints = tour.getOrderedLocations().stream()
                .filter(c -> c instanceof TourPoint)
                .map(c -> (TourPoint) c)
                .toList();
        Pace horizontalHeuristic = SpeedHeuristics.getHorizontalHeuristic(tourPoints);
        Pace climbingHeuristic = SpeedHeuristics.getClimbingHeuristic(tourPoints, horizontalHeuristic);
        Pace descendingHeuristic = SpeedHeuristics.getDescendingHeuristic(tourPoints, horizontalHeuristic);
        return new PersonalSpeed(horizontalHeuristic,climbingHeuristic,descendingHeuristic);
    }

}
