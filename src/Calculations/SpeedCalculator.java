package Calculations;

import WayModel.Location;
import WayModel.Pace;
import WayModel.TourPoint;

import java.time.Duration;
import java.util.Date;

//TODO implementation der Methoden
public class SpeedCalculator {

    public static Pace predictSpeed(Location a, Location b){
        long timeInSeconds = TimePrediction.predictTime(a,b).toSeconds();
        double timeInHours = timeInSeconds / 3600;
        double distance = DistanceCalculator.calc3dDistance(a,b);
        return new Pace(distance / timeInHours);
    }

    private static Pace calculateSpeed(TourPoint a, TourPoint b){ //TODO unfuck unit
        return new Pace(DistanceCalculator.calc3dDistance(a,b)/(calculateTime(a,b).getSeconds()/3600));
    }

    private static Duration calculateTime(TourPoint a, TourPoint b){
        long timeDiff = b.getTime().getTime() - a.getTime().getTime();
        return Duration.ofMillis(timeDiff);
    }
}
