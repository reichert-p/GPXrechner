package test.Calculations;

import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.WayModel.ElevationGain;
import GPXrechner.WayModel.Entities.Track;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Units.Distance;
import GPXrechner.WayModel.Units.Elevation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceCalculatorTest {
    Track mountainTrack;

    @BeforeEach
    void init() {
        mountainTrack = GetTracks.getMountainTrack();
    }

    @Test
    void calc2dDistance() {
        Distance distanceToFirstHut2D = DistanceCalculator.calc2dDistance(mountainTrack.getOrderedLocations().get(0), mountainTrack.getOrderedLocations().get(1));
        assertEquals(308, distanceToFirstHut2D.getValue(), 1);

        Distance distanceOfWholeTrack2D = DistanceCalculator.calc2dDistance(mountainTrack);
        assertEquals(7795, distanceOfWholeTrack2D.getValue(), 10);
    }

    @Test
    void calc3dDistance() {
        Distance distanceToFirstHut2D = DistanceCalculator.calc3dDistance(mountainTrack.getOrderedLocations().get(0), mountainTrack.getOrderedLocations().get(1));
        assertEquals(310, distanceToFirstHut2D.getValue(), 1);

        Distance distanceOfWholeTrack3D = DistanceCalculator.calc3dDistance(mountainTrack);
        assertEquals(8315, distanceOfWholeTrack3D.getValue(), 10);
    }

    @Test
    void calcElevationGain() {
        ArrayList<Location> locations = mountainTrack.getOrderedLocations();
        ElevationGain uphillSection = DistanceCalculator.calcElevationGain(locations.get(1), locations.get(2)); //uphill
        assertEquals(569, uphillSection.getUp(), 1);
        assertEquals(0, uphillSection.getDown(), 1);

        ElevationGain downhillSection = DistanceCalculator.calcElevationGain(locations.get(4), locations.get(5)); //downhill
        assertEquals(0, downhillSection.getUp(), 1);
        assertEquals(539, downhillSection.getDown(), 1);

        ElevationGain wholeTrack = DistanceCalculator.calcElevationGain(mountainTrack); //whole Track
        assertEquals(1346, wholeTrack.getUp(), 1);
        assertEquals(1493, wholeTrack.getDown(), 1);
    }

    @Test
    void calcAltitudeData() throws InsufficientDataException {
        List<Location> locations = mountainTrack.getOrderedLocations();
        Elevation lowestPoint = DistanceCalculator.calcMinAlt(locations);
        assertEquals(1096, lowestPoint.getValue(), 1);

        Elevation highestPoint = DistanceCalculator.calcMaxAlt(locations);
        assertEquals(2589, highestPoint.getValue(), 1);

        Elevation averageAltitude = DistanceCalculator.calcAvgAlt(locations);
        assertEquals(1778, averageAltitude.getValue(), 1);
    }
}