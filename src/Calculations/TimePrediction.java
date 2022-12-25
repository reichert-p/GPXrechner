package Calculations;

import Entities.Path;
import WayModel.AltitudeGain;
import WayModel.Location;
import WayModel.Units.Distance;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class TimePrediction {
    private static final int meterPerHour = 4000; //TODO für Fahrräder anpassen evtl
    private static final int climbPerHour = 400;
    private static final int descentPerHour = 600;

    /**
     * @return Time in Hours
     */
    public static Duration predictTime(Location a, Location b){
        Duration HorizontalTime = predictHorizontalTime(a,b);
        Duration VerticalTime = predictVerticalTime(a,b);
        long LongTime = Math.max(HorizontalTime.getSeconds(), VerticalTime.getSeconds());
        long ShortTime = Math.min(HorizontalTime.getSeconds(), VerticalTime.getSeconds());
        Duration WholeTime = Duration.ofSeconds((long)(LongTime + 0.5 * ShortTime));
        return WholeTime;
    }

    public static Duration predictTime(Path path){
        ArrayList<Location> locations = path.getOrderedLocations();
        Duration totalTime = Duration.ZERO;
        for (int i = 1; i < locations.size();i++){
            totalTime = totalTime.plus(predictTime(locations.get(i-1), locations.get(i)));
        }
        return totalTime;
    }

    /**
     * calculates the time needed without elevation
     * @return time in hours
     */
    private static Duration predictHorizontalTime(Location a, Location b){
        Distance distance = DistanceCalculator.calc2dDistance(a,b);
        return Duration.ofSeconds((long)(distance.getValue()/meterPerHour * 60 * 60));
    }

    private static Duration predictVerticalTime(Location a, Location b){
        AltitudeGain elevationChange = DistanceCalculator.calcElevationGain(a,b);
        double totalVerticalTime = 0;
        totalVerticalTime += elevationChange.getUp() / climbPerHour * 60 * 60;
        totalVerticalTime += elevationChange.getDown() / descentPerHour * 60 * 60; // TODO wie sieht dass denn aus?
        return Duration.ofSeconds((long)totalVerticalTime);
    }
}
