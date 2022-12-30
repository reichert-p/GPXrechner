package GPXrechner.Calculations;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Entities.Path;
import GPXrechner.WayModel.AltitudeGain;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Units.Distance;
import GPXrechner.WayModel.Units.Pace;

import java.time.Duration;
import java.util.ArrayList;

public class TimePrediction {
    private Pace horizontalSpeed; //TODO für Fahrräder anpassen evtl
    private Pace climbingSpeed;
    private Pace descendingSpeed;

    public TimePrediction(MovementSpeed movementSpeed){
        this.horizontalSpeed = movementSpeed.getHorizontalSpeed();
        this.climbingSpeed = movementSpeed.getClimbingSpeed();
        this.descendingSpeed = movementSpeed.getDescendingSpeed();
    }
    /**
     * @return Time in Hours
     */
    public Duration predictTime(Location a, Location b){
        Duration HorizontalTime = predictHorizontalTime(a,b);
        Duration VerticalTime = predictVerticalTime(a,b);
        long LongTime = Math.max(HorizontalTime.getSeconds(), VerticalTime.getSeconds());
        long ShortTime = Math.min(HorizontalTime.getSeconds(), VerticalTime.getSeconds());
        Duration WholeTime = Duration.ofSeconds((long)(LongTime + 0.5 * ShortTime));
        return WholeTime;
    }

    public Duration predictTime(Path path){
        ArrayList<Location> locations = path.getOrderedLocations();
        Duration totalTime = Duration.ZERO;
        for (int i = 1; i < locations.size();i++){
            totalTime = totalTime.plus(predictTime(locations.get(i-1), locations.get(i)));
        }
        return totalTime;
    }

    private Duration predictHorizontalTime(Location a, Location b){
        Distance distance = DistanceCalculator.calc2dDistance(a,b);
        return Duration.ofSeconds((long)(distance.getValue() / horizontalSpeed.getValue() * 60 * 60));
    }

    private Duration predictVerticalTime(Location a, Location b){
        AltitudeGain elevationChange = DistanceCalculator.calcElevationGain(a,b);
        double totalVerticalTime = 0;
        totalVerticalTime += elevationChange.getUp() / climbingSpeed.getValue() * 60 * 60;
        totalVerticalTime += elevationChange.getDown() / descendingSpeed.getValue() * 60 * 60; // TODO wie sieht dass denn aus?
        return Duration.ofSeconds((long)totalVerticalTime);
    }
}
