package GPXrechner.Calculations;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.PersonalSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Units.Pace;
import GPXrechner.WayModel.TourPoint;

import java.time.Duration;
import java.util.List;


public class SpeedCalculator {

    public static Pace predictSpeed(Location a, Location b, MovementSpeed speed){
        long timeInHours = TimePrediction.predictTime(a,b,speed).toHours();
        double distance = DistanceCalculator.calc3dDistance(a,b).getValue();
        return new Pace(distance / timeInHours);
    }

    public static PersonalSpeed predictPersonalMovementSpeed (Tour tour){
        List<TourPoint> tourPoints = tour.getOrderedLocations().stream()
                .filter(c -> c instanceof TourPoint)
                .map(c -> (TourPoint) c)
                .toList();
        Pace horizontalHeuristic = SpeedHeuristics.getHorizontalHeuristic(tourPoints);
        Pace climbingHeuristic = SpeedHeuristics.getClimbingHeuristic(tourPoints, horizontalHeuristic);
        Pace descendingHeuristic = SpeedHeuristics.getDescendingHeuristic(tourPoints, horizontalHeuristic);
        return new PersonalSpeed(horizontalHeuristic,climbingHeuristic,descendingHeuristic);
    }

    public static PersonalSpeed predictPersonalMovementSpeed (Tour[] tours){
        Double horizontalSum = 0.0;
        Double climbingSum = 0.0;
        Double descendingSum = 0.0;
        long weight = 0;
        for (Tour tour: tours) {
            Duration duration = TimePrediction.predictTime(tour, Sport.HIKING);
            long specifiedWeight = duration.toMinutes();
            PersonalSpeed specificSpeed = predictPersonalMovementSpeed(tour);
            weight += specifiedWeight;
            horizontalSum += specifiedWeight * specificSpeed.getHorizontalSpeed().getValue();
            climbingSum += specifiedWeight * specificSpeed.getClimbingSpeed().getValue();
            descendingSum += specifiedWeight * specificSpeed.getDescendingSpeed().getValue();
        }
        return new PersonalSpeed(
                new Pace(horizontalSum / weight),
                new Pace(climbingSum / weight),
                new Pace(descendingSum / weight)
        );
    }

    public static Double calculateSpeedDeviation(Tour tour, List<TourPoint> section){
        MovementSpeed generalSpeed = predictPersonalMovementSpeed(tour);
        Tour sectionTour = new Tour("");
        sectionTour.addTourPoints((TourPoint[]) section.toArray());
        Duration predictedTime = TimePrediction.predictTime(sectionTour,generalSpeed); //predicted time for section
        Duration actualTime = Duration.between(section.get(0).getTime(),section.get(section.size()-1).getTime()); //actual time taken
        return predictedTime.getSeconds() / (double)actualTime.getSeconds();
    }
}
