package GPXrechner;

import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Entities.Track;
import GPXrechner.WayModel.TrackPoint;

public class Main {
    public static void main(String[] args) {
        System.out.println(SpeedCalculator.predictSpeed( new TrackPoint(45,45,0),new TrackPoint(46,46,100), Sport.HIKING));
    }

    public static Track getRandomTrack(){
       Track t =  new Track();
       TrackPoint[] pointlist = {
               new TrackPoint(45,45,0),
               new TrackPoint(46,46,10),
               new TrackPoint(47,47,5),
               new TrackPoint(45,45,-10),
               new TrackPoint(46,46,0),
               new TrackPoint(47,47,20),
               new TrackPoint(45,45,35),
               new TrackPoint(46,46,30),
               new TrackPoint(47,47,15),
               new TrackPoint(45,45,5),
               new TrackPoint(46,46,0),
               new TrackPoint(47,47,0)};
       t.addTrackPoints(pointlist);
       return t;
    }


}