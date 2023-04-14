package GPXrechner.Domain.Calculations.TourSplitting;

import GPXrechner.Domain.WayModel.WayPoint;

import java.util.ArrayList;

public class WayPointSet {

    String description;
    private final ArrayList<WayPoint> wayPoints = new ArrayList<>();

    public WayPointSet(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public void addWayPoint(WayPoint t) {
        wayPoints.add(t);
    }

    public ArrayList<WayPoint> getWayPoints() {
        return wayPoints;
    }
}
