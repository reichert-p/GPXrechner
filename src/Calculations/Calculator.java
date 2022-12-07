package Calculations;

import WayModel.Location;

public class Calculator {
    /**
     * Simple Berechnung unter der Annahme dass Distanzen über 20 km nicht benötigt werden
     *TODO besser machen
     */

    private static double calc2dDistance(Location a, Location b){
        double latDistKm = Math.abs(111.3 * (a.getLat().getValue() - b.getLat().getValue()));
        double curvFact = 111.3 * Math.cos((b.getLat().getValue() + b.getLat().getValue()) / 2);
        double lonDistKm = curvFact * Math.abs((a.getLon().getValue() - b.getLon().getValue()));
        return Math.sqrt(lonDistKm * lonDistKm + latDistKm * latDistKm);
    }

    private static double calcElevationGain(Location a, Location b){
        return b.getEle().getValue() - a.getEle().getValue();
    }

    private static double calc3dDistance(Location a, Location b){
        return Math.sqrt(Math.pow(calcElevationGain(a,b),2)+Math.pow(calc2dDistance(a,b),2));
    }
}
