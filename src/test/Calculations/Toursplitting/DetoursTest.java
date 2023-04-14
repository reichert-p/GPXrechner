package test.Calculations.Toursplitting;

import GPXrechner.Domain.Calculations.TourSplitting.Detours;
import GPXrechner.Domain.Calculations.TourSplitting.TimeHeuristic;
import GPXrechner.Domain.Calculations.TourSplitting.WayPointSet;
import GPXrechner.Domain.WayModel.Entities.Path;
import org.junit.jupiter.api.Test;
import test.GetTracks;
import test.GetWayPointSets;

import static org.junit.jupiter.api.Assertions.*;

class DetoursTest {
    @Test
    void initDetours(){
        Path path = GetTracks.getMountainTrack();
        WayPointSet wayPointSet = GetWayPointSets.getHochvogelHuts();

        TimeHeuristic timeHeuristicMock = new timeHeuristicMock();
        Detours detours = Detours.initDetours(path,wayPointSet, timeHeuristicMock);
        var possibleDetours = detours.getPossibleDetours();
        assertEquals(2, possibleDetours.size());
        var firstDetour = possibleDetours.get(0);
        assertEquals("Prinz-Luipold-Haus",firstDetour.getDestination().toString());
        assertEquals(0 ,firstDetour.getExpenditure().toSeconds());
        assertEquals(0 ,firstDetour.getPosition());
    }
}