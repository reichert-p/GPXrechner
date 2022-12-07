package Calculations;

import WayModel.Location;

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

    /**
     * calculates three dimensional distance
     * @return Distance between two Locations in meter
     */
    public static double calc3dDistance(Location a, Location b){
        return Math.sqrt(Math.pow(calcElevationGain(a,b),2)+Math.pow(calc2dDistance(a,b),2));
    }
}
