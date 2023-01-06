package GPXrechner.Calculations;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.ElevationGain;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Units.Distance;

import java.time.Duration;
import java.util.ArrayList;

public class TimePrediction {
    public static Duration predictTime(Location a, Location b, MovementSpeed movementSpeed){
        Duration HorizontalTime = predictHorizontalTime(a,b,movementSpeed);
        Duration VerticalTime = predictVerticalTime(a,b,movementSpeed);
        long LongTime = Math.max(HorizontalTime.getSeconds(), VerticalTime.getSeconds());
        long ShortTime = Math.min(HorizontalTime.getSeconds(), VerticalTime.getSeconds());
        Duration WholeTime = Duration.ofSeconds((long)(LongTime + 0.5 * ShortTime));
        return WholeTime;
    }

    public static Duration predictTime(Path path,MovementSpeed movementSpeed){
        ArrayList<Location> locations = path.getOrderedLocations();
        Duration totalTime = Duration.ZERO;
        for (int i = 1; i < locations.size();i++){
            totalTime = totalTime.plus(predictTime(locations.get(i-1), locations.get(i),movementSpeed));
        }
        return totalTime;
    }

    private static Duration predictHorizontalTime(Location a, Location b,MovementSpeed movementSpeed){
        Distance distance = DistanceCalculator.calc2dDistance(a,b);
        return Duration.ofSeconds((long)(distance.getValue() / movementSpeed.getHorizontalSpeed().getValue() * 60 * 60));
    }

    private static Duration predictVerticalTime(Location a, Location b,MovementSpeed movementSpeed){
        ElevationGain elevationChange = DistanceCalculator.calcElevationGain(a,b);
        double totalVerticalTime = 0;
        totalVerticalTime += elevationChange.getUp() / movementSpeed.getClimbingSpeed().getValue() * 60 * 60;
        totalVerticalTime += elevationChange.getDown() / movementSpeed.getDescendingSpeed().getValue() * 60 * 60;
        return Duration.ofSeconds((long)totalVerticalTime);
    }
}
