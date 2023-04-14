package GPXrechner.Domain.Calculations;

import GPXrechner.Domain.WayModel.ElevationGain;
import GPXrechner.Domain.WayModel.Entities.Path;
import GPXrechner.Domain.WayModel.Location;
import GPXrechner.Domain.WayModel.Units.Distance;
import GPXrechner.Domain.WayModel.Units.Elevation;

import java.util.ArrayList;
import java.util.List;

public class DistanceCalculator {
    private static final int equatorLength = 40075017;
    private static final int equatorDegree = equatorLength / 360;

    public static Distance calc2dDistance(Location a, Location b) {
        Distance latDistKm = calcDeltaLat(a, b);
        Distance lonDistKm = calcDeltaLon(a, b);
        return new Distance(Math.sqrt(Math.pow(lonDistKm.getValue(), 2) + Math.pow(latDistKm.getValue(), 2)));
    }

    public static Distance calc2dDistance(Path path) {
        ArrayList<Location> locations = path.getOrderedLocations();
        Distance total2dDistance = new Distance(0);
        for (int i = 1; i < locations.size(); i++) {
            total2dDistance = total2dDistance.addDistance(calc2dDistance(locations.get(i - 1), locations.get(i)));
        }
        return total2dDistance;
    }

    private static Distance calcDeltaLon(Location a, Location b) {
        double lonDistSex = a.getLon().getValue() - b.getLon().getValue(); //get distance in sexagesimal system
        lonDistSex = Math.abs(lonDistSex);                                //distance between two points should be positive
        double avgLat = (b.getLat().getValue() + b.getLat().getValue()) / 2; //distance between two Longitudes depends on Latitude
        double lonFact = equatorDegree * Math.cos(avgLat * (Math.PI / 180));   //calculates distance between two Longitudes
        return new Distance(lonFact * lonDistSex); //convert into m
    }

    private static Distance calcDeltaLat(Location a, Location b) {
        double latDistSex = a.getLat().getValue() - b.getLat().getValue();
        latDistSex = Math.abs(latDistSex);
        return new Distance(Math.abs(equatorDegree * latDistSex));
    }

    public static ElevationGain calcElevationGain(Location a, Location b) {
        return new ElevationGain(b.getEle().getValue() - a.getEle().getValue());
    }

    public static ElevationGain calcElevationGain(Path path) {
        ArrayList<Location> locations = path.getOrderedLocations();
        ElevationGain totalElevationDiff = new ElevationGain(0, 0);
        for (int i = 1; i < locations.size(); i++) {
            ElevationGain diff = calcElevationGain(locations.get(i - 1), locations.get(i));
            totalElevationDiff = totalElevationDiff.addElevationGain(diff);
        }
        return totalElevationDiff;
    }

    public static Distance calc3dDistance(Location a, Location b) {
        return new Distance(Math.sqrt(Math.pow(calcElevationGain(a, b).getManhattenNorm(), 2) + Math.pow(calc2dDistance(a, b).getValue(), 2)));
    }

    public static Distance calc3dDistance(Path path) {
        ArrayList<Location> locations = path.getOrderedLocations();
        Distance total3dDistance = new Distance(0);
        for (int i = 1; i < locations.size(); i++) {
            total3dDistance = total3dDistance.addDistance(calc3dDistance(locations.get(i - 1), locations.get(i)));
        }
        return total3dDistance;
    }

    public static Elevation calcAvgAlt(List<Location> list) throws InsufficientDataException {
        if (list.size() == 0) {
            throw new InsufficientDataException();
        }
        double sum = 0;
        for (Location l : list) {
            sum += l.getEle().getValue();
        }
        return new Elevation(sum / list.size());
    }

    public static Elevation calcMinAlt(List<Location> list) throws InsufficientDataException { //TODO this alt should be elevation/ele
        if (list.size() == 0) {
            throw new InsufficientDataException();
        }
        double min = 10000;
        for (Location l : list) {
            min = Math.min(min, l.getEle().getValue());
        }
        return new Elevation(min);
    }

    public static Elevation calcMaxAlt(List<Location> list) throws InsufficientDataException {
        if (list.size() == 0) {
            throw new InsufficientDataException();
        }
        double max = -1000;
        for (Location l : list) {
            max = Math.max(max, l.getEle().getValue());
        }
        return new Elevation(max);
    }
}
