package GPXrechner.WayModel.Entities;

import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.TourPoint;
import GPXrechner.WayModel.TrackPoint;

import java.util.ArrayList;
import java.util.Arrays;

public class Tour implements Path {
    private String description;
    private ArrayList<Location> tourPoints = new ArrayList<>();

    public Tour(String description) {
        this.description = description;
    }

    public void addTourPoints(TourPoint[] t){
        tourPoints.addAll(Arrays.stream(t).toList());
    }

    public void addTourPoint(TourPoint t){
        tourPoints.add(t);
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public ArrayList<Location> getOrderedLocations() {
        return tourPoints;
    }

    public Track getTrack(){
        Track output = new Track(description);
        for (Location tp:tourPoints) {
            output.addTrackPoint(new TrackPoint(
                    tp.getLat(),
                    tp.getLon(),
                    tp.getEle()
            ));
        }
        return output;
    }
}
