package test.Calculations;

import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.WayModel.ElevationGain;
import GPXrechner.WayModel.Entities.Track;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Units.Distance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceCalculatorTest {
    Track mountainTrack;

    @BeforeEach
    void init(){
        mountainTrack = GetTracks.getMountainTrack();
    }

    @Test
    void calc2dDistance() {
        Distance d1 = DistanceCalculator.calc2dDistance(mountainTrack.getOrderedLocations().get(0),mountainTrack.getOrderedLocations().get(1));
        assertEquals(308, Math.round(d1.getValue()));
        Distance dAll = DistanceCalculator.calc2dDistance(mountainTrack);
        double dist = dAll.getValue() / 100;
        assertEquals(78, Math.round(dist));
    }

    @Test
    void calc3dDistance() {
        Distance d1 = DistanceCalculator.calc3dDistance(mountainTrack.getOrderedLocations().get(0),mountainTrack.getOrderedLocations().get(1));
        assertEquals(310, Math.round(d1.getValue()));
        Distance dAll = DistanceCalculator.calc3dDistance(mountainTrack);
        double dist = dAll.getValue() / 100;
        assertEquals(83, Math.round(dist));
    }

    @Test
    void calcElevationGain() {
        ArrayList<Location> locations = mountainTrack.getOrderedLocations();
        ElevationGain a1 = DistanceCalculator.calcElevationGain(locations.get(1),locations.get(2)); //uphill
        ElevationGain a2 = DistanceCalculator.calcElevationGain(locations.get(4), locations.get(5)); //downhill
        ElevationGain a3 = DistanceCalculator.calcElevationGain(mountainTrack); //whole Track
        assertEquals(569, Math.round(a1.getUp()));
        assertEquals(0, a1.getDown());
        assertEquals(0, a2.getUp());
        assertEquals(539, Math.round(a2.getDown()));
        assertEquals(1346,Math.round(a3.getUp()));
        assertEquals(1493, Math.round(a3.getDown()));
    }

    @Test
    void calcAlts() throws InsufficientDataException {
        List<Location> locations =mountainTrack.getOrderedLocations();
        assertEquals(1096,Math.round(DistanceCalculator.calcMinAlt(locations).getValue()));
        assertEquals(2589,Math.round(DistanceCalculator.calcMaxAlt(locations).getValue()));
        assertEquals(1778,Math.round(DistanceCalculator.calcAvgAlt(locations).getValue()));

    }

}