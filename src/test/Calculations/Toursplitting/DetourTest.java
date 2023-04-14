package test.Calculations.Toursplitting;

import GPXrechner.Domain.Calculations.MovementSpeed.Sport;
import GPXrechner.Domain.Calculations.TourSplitting.Detour;
import GPXrechner.Domain.Calculations.TourSplitting.DirectWayHeuristic;
import GPXrechner.Domain.Calculations.TourSplitting.TimeHeuristic;
import GPXrechner.Domain.WayModel.Entities.Path;
import GPXrechner.Domain.WayModel.WayPoint;
import org.junit.jupiter.api.Test;
import test.GetTracks;


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