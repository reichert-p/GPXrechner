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

    public static PersonalSpeed predictPersonalMovementSpeed(Tour tour) throws InsufficientDataException{
        List<TourPoint> tourPoints = tour.getOrderedLocations().stream()
                .filter(c -> c instanceof TourPoint)
                .map(c -> (TourPoint) c)
                .toList();
        Pace horizontalHeuristic = SpeedHeuristics.getHorizontalHeuristic(tourPoints);
        Pace climbingHeuristic = SpeedHeuristics.getClimbingHeuristic(tourPoints, horizontalHeuristic);
        Pace descendingHeuristic = SpeedHeuristics.getDescendingHeuristic(tourPoints, horizontalHeuristic);
        return new PersonalSpeed(horizontalHeuristic,climbingHeuristic,descendingHeuristic);
    }

    public static PersonalSpeed predictPersonalMovementSpeed(Tour[] tours) throws InsufficientDataException{
        Double horizontalSum = 0.0;
        Double climbingSum = 0.0;
        Double descendingSum = 0.0;
        long weight = 0;
        for (Tour tour: tours) {
            Duration duration = TimePrediction.predictTime(tour, Sport.HIKING);
            long specifiedWeight = duration.toMinutes();
            try {
                PersonalSpeed specificSpeed = predictPersonalMovementSpeed(tour);
                weight += specifiedWeight;
                horizontalSum += specifiedWeight * specificSpeed.getHorizontalSpeed().getValue();
                climbingSum += specifiedWeight * specificSpeed.getClimbingSpeed().getValue();
                descendingSum += specifiedWeight * specificSpeed.getDescendingSpeed().getValue();
            }catch (InsufficientDataException e){

            }
        }
        if (weight != 0){
            return new PersonalSpeed(
                    new Pace(horizontalSum / weight),
                    new Pace(climbingSum / weight),
                    new Pace(descendingSum / weight)
            );
        }
        throw new InsufficientDataException();
    }

    public static Double calculateSpeedDeviation(Tour tour, Tour section)throws InsufficientDataException{
        if (section.getOrderedLocations().isEmpty()){
            throw new InsufficientDataException();
        }
        MovementSpeed generalSpeed = predictPersonalMovementSpeed(tour);
        Duration predictedTime = TimePrediction.predictTime(section,generalSpeed); //predicted time for section
        Duration actualTime = section.getDuration(); //actual time taken
        return predictedTime.getSeconds() / (double)actualTime.getSeconds();
    }
}
