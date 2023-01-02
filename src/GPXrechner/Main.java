package GPXrechner;

import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TimePrediction;
import GPXrechner.Entities.Track;
import GPXrechner.Inputhandling.Parsing.DOMParser;
import GPXrechner.WayModel.AltitudeGain;
import GPXrechner.WayModel.Units.Distance;

import java.sql.Time;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        DOMParser parser = new DOMParser();
        Track westweg = parser.parseTrack("Files\\GPX\\Track_Westweg.gpx");
        Distance dist = DistanceCalculator.calc3dDistance(westweg);
        AltitudeGain ele = DistanceCalculator.calcElevationGain(westweg);
        TimePrediction pred = new TimePrediction(Sport.HIKING);
        Duration dur = pred.predictTime(westweg);
        System.out.println("haha");
    }

}