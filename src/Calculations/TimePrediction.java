package Calculations;

import Entities.Path;
import WayModel.Location;

import java.util.ArrayList;

public class TimePrediction {
    private static final int meterPerHour = 4000; //TODO für Fahrräder anpassen evtl
    private static final int climbPerHour = 400;
    private static final int descentPerHour = 600;

    /**
     * @return Time in Hours
     */
    public static double predictTime(Location a, Location b){
        double HorizontalTime = predictHorizontalTime(a,b);
        double VerticalTime = predictVerticalTime(a,b);
        double LongTime = Math.max(HorizontalTime, VerticalTime);
        double ShortTime = Math.min(HorizontalTime, VerticalTime);
        double WholeTime = LongTime + 0.5 * ShortTime;
        return WholeTime;
    }

    /**
     * @return Time in Hours
     */
    public static double predictTime(Path path){
        ArrayList<Location> locations = path.getOrderedLocations();
        double totalTime = 0;
        for (int i = 1; i < locations.size();i++){
            totalTime += predictTime(locations.get(i-1), locations.get(i));
        }
        return totalTime;
    }

    /**
     * calculates the time needed without elevation
     * @return time in hours
     */
    private static double predictHorizontalTime(Location a, Location b){
        double distance = DistanceCalculator.calc2dDistance(a,b);
        return distance / meterPerHour;
    }

    private static double predictVerticalTime(Location a, Location b){
        double elevationChange = DistanceCalculator.calcElevationGain(a,b);
        if (elevationChange > 0){
            return elevationChange / climbPerHour;
        }
        if (elevationChange <0){
            return elevationChange / descentPerHour;
        }
        return 0;
    }
}
