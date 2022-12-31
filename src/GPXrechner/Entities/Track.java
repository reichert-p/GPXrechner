package GPXrechner.Entities;

import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.TrackPoint;

import java.util.ArrayList;
import java.util.Arrays;

public class Track implements Path{
    private String description;
    private ArrayList<Location> trackPoints = new ArrayList<>();

    public Track(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public ArrayList<Location> getOrderedLocations() {
        return trackPoints;
    }

    public void addTrackPoints(TrackPoint[] t){
        trackPoints.addAll(Arrays.stream(t).toList());
    }

    public void addTrackPoint(TrackPoint t){
        trackPoints.add(t);
    }
}
