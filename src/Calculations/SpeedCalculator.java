package Calculations;

import WayModel.Location;
import WayModel.Units.Pace;
import WayModel.TourPoint;

import java.time.Duration;

//TODO implementation der Methoden
public class SpeedCalculator {

    public static Pace predictSpeed(Location a, Location b){
        long timeInHours = TimePrediction.predictTime(a,b).toHours();
        double distance = DistanceCalculator.calc3dDistance(a,b).getValue();
        return new Pace(distance / timeInHours);
    }

    private static Pace calculateSpeed(TourPoint a, TourPoint b){
        return new Pace(DistanceCalculator.calc3dDistance(a,b).getValue()/(calculateTime(a,b).toHours()));
    }

    private static Duration calculateTime(TourPoint a, TourPoint b){
        long timeDiff = b.getTime().getTime() - a.getTime().getTime();
        return Duration.ofMillis(timeDiff);
    }
}
