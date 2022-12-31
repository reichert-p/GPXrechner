package GPXrechner.Calculations;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.PersonalSpeed;
import GPXrechner.Entities.Tour;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Units.Pace;
import GPXrechner.WayModel.TourPoint;

import java.sql.Time;
import java.time.Duration;
import java.util.List;


public class SpeedCalculator {

    public static Pace predictSpeed(Location a, Location b, MovementSpeed speed){
        TimePrediction timePrediction = new TimePrediction(speed);
        long timeInHours = timePrediction.predictTime(a,b).toHours();
        double distance = DistanceCalculator.calc3dDistance(a,b).getValue();
        return new Pace(distance / timeInHours);
    }

    public static MovementSpeed predictPersonalMovementSpeed (Tour tour){ //TODO make incomplete movementspeeds possible?
        List<TourPoint> tourPoints = tour.getOrderedLocations().stream()
                .filter(c -> c instanceof TourPoint)
                .map(c -> (TourPoint) c)
                .toList();
        Pace horizontalHeuristic = SpeedHeuristics.getHorizontalHeuristic(tourPoints);
        Pace climbingHeuristic = SpeedHeuristics.getClimbingHeuristic(tourPoints, horizontalHeuristic);
        Pace descendingHeuristic = SpeedHeuristics.getDescendingHeuristic(tourPoints, horizontalHeuristic);
        return new PersonalSpeed(horizontalHeuristic,climbingHeuristic,descendingHeuristic);
    }

    public static Double calculateSpeedDeviation(Tour tour, List<TourPoint> section){
        MovementSpeed generalSpeed = predictPersonalMovementSpeed(tour);
        Tour sectionTour = new Tour("");
        sectionTour.addTourPoints((TourPoint[]) section.toArray());
        TimePrediction timePrediction = new TimePrediction(generalSpeed);
        Duration predictedTime = timePrediction.predictTime(sectionTour); //predicted time for section
        Duration actualTime = Duration.between(section.get(0).getTime(),section.get(section.size()-1).getTime()); //actual time taken
        return predictedTime.getSeconds() / (double)actualTime.getSeconds();
    }

}
