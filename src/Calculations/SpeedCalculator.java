package Calculations;

import WayModel.Location;
import WayModel.TourPoint;

import java.time.Duration;
import java.util.Date;

//TODO implementation der Methoden
public class SpeedCalculator {
    /**
     * @return predicted actual Speed in m/h
     */
    public static double predictSpeed(Location a, Location b){
        return DistanceCalculator.calc3dDistance(a,b) / TimePrediction.predictTime(a,b);
    }

    private static double calculateSpeed(TourPoint a, TourPoint b){ //TODO unfuck unit
        return DistanceCalculator.calc3dDistance(a,b)/calculateTime(a,b).getTime();
    }

    private static Date calculateTime(TourPoint a, TourPoint b){
        long timeDiff = b.getTime().getTime() - a.getTime().getTime();
        return new Date(timeDiff);
    }
}
