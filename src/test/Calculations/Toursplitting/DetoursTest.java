package test.Calculations.Toursplitting;

import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TourSplitting.Detours;
import GPXrechner.Calculations.TourSplitting.DirectWayHeuristic;
import GPXrechner.Calculations.TourSplitting.TimeHeuristic;
import GPXrechner.Calculations.TourSplitting.WayPointSet;
import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.Location;
import org.junit.jupiter.api.Test;
import test.GetTracks;
import test.GetWayPointSets;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DetoursTest {

    @Test
    void addDetour() {
    }

    @Test
    void getPossibleDetours() {
    }

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