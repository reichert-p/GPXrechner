package test.Calculations.Toursplitting;

import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TourSplitting.Detour;
import GPXrechner.Calculations.TourSplitting.DirectWayHeuristic;
import GPXrechner.Calculations.TourSplitting.TimeHeuristic;
import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.WayPoint;
import org.junit.jupiter.api.Test;
import test.Calculations.GetTracks;


import static org.junit.jupiter.api.Assertions.*;

class DetourTest {
    @Test
    void DetourCreationTest(){
        TimeHeuristic timeHeuristic = new DirectWayHeuristic(Sport.HIKING);
        Path flatTrack = GetTracks.getStraightTrack();
        WayPoint detourDestination = new WayPoint(47.6,10.1,0,"Orthogonal to 3nd Trackpoint");

        Detour detour = Detour.createDetour(flatTrack,detourDestination,timeHeuristic);

        assertEquals("Orthogonal to 3nd Trackpoint" ,detour.getDestination().toString());
        assertEquals(2 ,detour.getPosition());
        assertEquals(112 ,detour.getExpenditure().toMinutes());
    }
}