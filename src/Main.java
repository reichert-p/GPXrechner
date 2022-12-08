import Calculations.DistanceCalculator;
import Calculations.TimePrediction;
import Entities.Tour;
import Entities.Track;
import WayModel.TourPoint;
import WayModel.TrackPoint;
import WayModel.WayPoint;

public class Main {
    public static void main(String[] args) {
        System.out.println(DistanceCalculator.calcElevationGain(getRandomTrack()));
    }

    public static Track getRandomTrack(){
       Track t =  new Track();
       TrackPoint[] pointlist = {new TrackPoint(45,45,100),new TrackPoint(46,46,200),new TrackPoint(47,47,50)};
       t.addTrackPoints(pointlist);
       return t;
    }


}