package GPXrechner.Calculations.TourSplitting;

import GPXrechner.WayModel.WayPoint;

import java.util.ArrayList;
import java.util.Arrays;

public class WayPointSet {

    String description;
    private ArrayList<WayPoint> wayPoints = new ArrayList<>();

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

    public ArrayList<WayPoint> getWayPoints() {
        return wayPoints;
    }
}
