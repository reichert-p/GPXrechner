package Calculations;

import Entities.Path;
import WayModel.AltitudeGain;
import WayModel.Location;

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
    public static double calc2dDistance(Location a, Location b){
        double latDistKm = calcDeltaLat(a,b);
        double lonDistKm = calcDeltaLon(a,b);
        return Math.sqrt(Math.pow(lonDistKm,2) + Math.pow(latDistKm ,2));
    }

    public static double calc2dDistance(Path path){
        ArrayList<Location> locations = path.getOrderedLocations();
        double total2dDistance = 0;
        for (int i = 1; i < locations.size();i++){
            total2dDistance += calc2dDistance(locations.get(i-1), locations.get(i));
        }
        return total2dDistance;
    }

    private static double calcDeltaLon(Location a, Location b) {
        double lonDistSex = a.getLon().getValue() - b.getLon().getValue(); //get distance in sexagesimal system
        lonDistSex = Math.abs(lonDistSex);                                //distance between two points should be positive
        double avgLat = (b.getLat().getValue() + b.getLat().getValue()) / 2; //distance between two Longitudes depends on Latitude
        double lonFact = equatorDegree * Math.cos(avgLat * (Math.PI/180));   //calculates distance between two Longitudes
        return lonFact * lonDistSex; //convert into m
    }

    private static double calcDeltaLat(Location a, Location b){
        double latDistSex = a.getLat().getValue() - b.getLat().getValue();
        latDistSex = Math.abs(latDistSex);
        return Math.abs(equatorDegree * latDistSex);
    }

    /**
     * @param a start
     * @param b end
     * @return altitude gain/loss in meters
     */
    public static double calcElevationGain(Location a, Location b){
        return b.getEle().getValue() - a.getEle().getValue();
    }

    public static AltitudeGain calcElevationGain(Path path){
        ArrayList<Location> locations = path.getOrderedLocations();
        double up = 0;
        double down = 0;
        for (int i = 1; i < locations.size();i++){
             double diff = calcElevationGain(locations.get(i-1), locations.get(i));
             if (diff < 0){
                 down -= diff;
             }
             if (diff > 0){
                 up += diff;
             }
        }
        return new AltitudeGain(up,down);
    }

    /**
     * calculates three dimensional distance
     * @return Distance between two Locations in meter
     */
    public static double calc3dDistance(Location a, Location b){
        return Math.sqrt(Math.pow(calcElevationGain(a,b),2)+Math.pow(calc2dDistance(a,b),2));
    }

    public static double calc3dDistance(Path path){
        ArrayList<Location> locations = path.getOrderedLocations();
        double total3dDistance = 0;
        for (int i = 1; i < locations.size();i++){
            total3dDistance += calc3dDistance(locations.get(i-1), locations.get(i));
        }
        return total3dDistance;
    }

    public static double calcAvgAlt(List<Location> list) {
        double sum = 0;
        for (Location l:list) {
            sum += l.getEle().getValue();
        }
        return sum/ list.size();
    }
}
