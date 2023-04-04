package test;

import GPXrechner.Calculations.TourSplitting.WayPointSet;
import GPXrechner.WayModel.WayPoint;

public class GetWayPointSets {

    public static WayPointSet getHochvogelHuts() { //Hochvogel traverse
        WayPointSet hochvogelHuts = new WayPointSet("huts");
        hochvogelHuts.addWayPoint(
                new WayPoint(
                        47.39244,
                        10.42010,
                        1880,
                        "Prinz-Luipold-Haus"
                )
        );
        hochvogelHuts.addWayPoint(
                new WayPoint(
                        47.37008,
                        10.45297,
                        1699,
                        "Schwabberghütte"
                )
        );
        return hochvogelHuts;
    }
}
