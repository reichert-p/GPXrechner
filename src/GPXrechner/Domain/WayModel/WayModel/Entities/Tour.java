package GPXrechner.Domain.WayModel.WayModel.Entities;

import GPXrechner.Domain.WayModel.WayModel.Location;
import GPXrechner.Domain.WayModel.WayModel.TourPoint;
import GPXrechner.Domain.WayModel.WayModel.TrackPoint;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class Tour implements Path {
    private final String description;
    private final ArrayList<Location> tourPoints = new ArrayList<>();

    public Tour(String description) {
        this.description = description;
    }

    public void addTourPoints(TourPoint[] t) {
        tourPoints.addAll(Arrays.stream(t).toList());
    }

    public void addTourPoint(TourPoint t) {
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

    public Track getTrack() {
        Track output = new Track(description);
        for (Location tp : tourPoints) {
            output.addTrackPoint(new TrackPoint(
                    tp.getLat(),
                    tp.getLon(),
                    tp.getEle()
            ));
        }
        return output;
    }

    public Duration getDuration() {
        TourPoint first = (TourPoint) tourPoints.get(0);
        TourPoint last = (TourPoint) tourPoints.get(tourPoints.size() - 1);
        return Duration.between(first.getTime(), last.getTime());
    }
}
