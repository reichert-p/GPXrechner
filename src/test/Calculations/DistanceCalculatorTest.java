package test.Calculations;

import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.WayModel.Entities.Track;
import GPXrechner.WayModel.AltitudeGain;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Units.Distance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DistanceCalculatorTest {
    Track mountainTreck;

    @BeforeEach
    void init(){
        mountainTreck = getTracks.getMountainTrack();
    }

    @Test
    void testCalc2dDistance() {
        Distance d1 = DistanceCalculator.calc2dDistance(mountainTreck.getOrderedLocations().get(0),mountainTreck.getOrderedLocations().get(1));
        assertEquals(308, Math.round(d1.getValue()));
        Distance dAll = DistanceCalculator.calc2dDistance(mountainTreck);
        double dist = dAll.getValue() / 100;
        assertEquals(78, Math.round(dist));
    }

    @Test
    void testCalcElevationGain() {
        ArrayList<Location> locations = mountainTreck.getOrderedLocations();
        AltitudeGain a1 = DistanceCalculator.calcElevationGain(locations.get(1),locations.get(2)); //uphill
        AltitudeGain a2 = DistanceCalculator.calcElevationGain(locations.get(4), locations.get(5)); //downhill
        AltitudeGain a3 = DistanceCalculator.calcElevationGain(mountainTreck); //whole Track
        assertEquals(569, Math.round(a1.getUp()));
        assertEquals(0, a1.getDown());
        assertEquals(0, a2.getUp());
        assertEquals(539, Math.round(a2.getDown()));
        assertEquals(1346,Math.round(a3.getUp()));
        assertEquals(1493, Math.round(a3.getDown()));
    }

}