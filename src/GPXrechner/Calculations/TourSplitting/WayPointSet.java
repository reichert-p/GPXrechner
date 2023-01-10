package GPXrechner.Calculations.TourSplitting;

import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.WayPoint;

import java.util.ArrayList;
import java.util.Arrays;

public class WayPointSet {

    String description;
    private ArrayList<Location> wayPoints = new ArrayList<>();

    public WayPointSet(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public void addWayPoints(WayPoint[] t){
        wayPoints.addAll(Arrays.stream(t).toList());
    }

    public void addWayPoint(WayPoint t){
        wayPoints.add(t);
    }

    public ArrayList<Location> getWayPoints() {
        return wayPoints;
    }
}
