package test.Calculations.Toursplitting;

import GPXrechner.Calculations.TourSplitting.WayPointSet;
import GPXrechner.WayModel.WayPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class WayPointSetTest {

    @Test
    public void creation(){
        WayPointSet testSet = new WayPointSet("Important Landmarks");
        Assertions.assertEquals("Important Landmarks", testSet.toString());
        Assertions.assertEquals(0 ,testSet.getWayPoints().size());

        testSet.addWayPoint(new WayPoint(28.9,86.8,8850,"Mount Everest"));
        testSet.addWayPoint(new WayPoint(45.8,6.85,4809,"Monte Bianco"));

        Assertions.assertEquals("Important Landmarks", testSet.toString());
        Assertions.assertEquals(2 ,testSet.getWayPoints().size());
        Assertions.assertEquals("Mount Everest", testSet.getWayPoints().get(0).toString());
        Assertions.assertEquals("Monte Bianco", testSet.getWayPoints().get(1).toString());
    }

}