package GPXrechner.Calculations;

import GPXrechner.Entities.Path;
import GPXrechner.WayModel.AltitudeGain;
import GPXrechner.WayModel.Units.Distance;
import GPXrechner.WayModel.Units.Elevation;
import GPXrechner.WayModel.Location;

import java.util.ArrayList;
import java.util.List;

public class DistanceCalculator {
    /**
     * Simple Kugeloberflächenberechnung nach Pythagoras
     */
    private static final int equatorLength = 40075017;
    private static final int equatorDegree = equatorLength / 360;

    /**
     * Doesn't account for heights, assumes distance as in a map
     * @return Distance between two Locations in meter
     */
    public static Distance calc2dDistance(Location a, Location b){
        Distance latDistKm = calcDeltaLat(a,b);
        Distance lonDistKm = calcDeltaLon(a,b);
        return new Distance(Math.sqrt(Math.pow(lonDistKm.getValue(),2) + Math.pow(latDistKm.getValue(),2)));
    }

    public static Distance calc2dDistance(Path path){
        ArrayList<Location> locations = path.getOrderedLocations();
        Distance total2dDistance = new Distance(0);
        for (int i = 1; i < locations.size();i++){
            total2dDistance.addDistance(calc2dDistance(locations.get(i-1), locations.get(i)));
        }
        return total2dDistance;
    }

    private static Distance calcDeltaLon(Location a, Location b) {
        double lonDistSex = a.getLon().getValue() - b.getLon().getValue(); //get distance in sexagesimal system
        lonDistSex = Math.abs(lonDistSex);                                //distance between two points should be positive
        double avgLat = (b.getLat().getValue() + b.getLat().getValue()) / 2; //distance between two Longitudes depends on Latitude
        double lonFact = equatorDegree * Math.cos(avgLat * (Math.PI/180));   //calculates distance between two Longitudes
        return new Distance(lonFact * lonDistSex); //convert into m
    }

    private static Distance calcDeltaLat(Location a, Location b){
        double latDistSex = a.getLat().getValue() - b.getLat().getValue();
        latDistSex = Math.abs(latDistSex);
        return new Distance(Math.abs(equatorDegree * latDistSex));
    }

    /**
     * @param a start
     * @param b end
     * @return altitude gain/loss in meters
     */
    public static AltitudeGain calcElevationGain(Location a, Location b){
        return new AltitudeGain(b.getEle().getValue() - a.getEle().getValue());
    }

    public static AltitudeGain calcElevationGain(Path path){
        ArrayList<Location> locations = path.getOrderedLocations();
        AltitudeGain totalAltitudeDiff = new AltitudeGain(0,0);
        for (int i = 1; i < locations.size();i++){
            AltitudeGain diff = calcElevationGain(locations.get(i-1), locations.get(i));
            totalAltitudeDiff.addAltitude(diff);
        }
        return totalAltitudeDiff;
    }

    /**
     * calculates three dimensional distance
     * @return Distance between two Locations in meter
     */
    public static Distance calc3dDistance(Location a, Location b){ //TODO improve this abomination
        return new Distance(Math.sqrt(Math.pow(calcElevationGain(a,b).getManhattenNorm(),2)+Math.pow(calc2dDistance(a,b).getValue(),2)));
    }

    public static Distance calc3dDistance(Path path){
        ArrayList<Location> locations = path.getOrderedLocations();
        Distance total3dDistance = new Distance(0);
        for (int i = 1; i < locations.size();i++){
            total3dDistance.addDistance(calc3dDistance(locations.get(i-1), locations.get(i)));
        }
        return total3dDistance;
    }

    public static Elevation calcAvgAlt(List<Location> list) {
        double sum = 0;
        for (Location l:list) {
            sum += l.getEle().getValue();
        }
        return new Elevation(sum/ list.size());
    }
}
