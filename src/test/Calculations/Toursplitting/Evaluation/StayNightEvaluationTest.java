package test.Calculations.Toursplitting.Evaluation;

import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TourSplitting.Detour;
import GPXrechner.Calculations.TourSplitting.Detours;
import GPXrechner.Calculations.TourSplitting.Evaluation.StayNightEvaluation;
import GPXrechner.Calculations.TourSplitting.Representation;
import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.WayPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.Calculations.GetTracks;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class StayNightEvaluationTest {
    @Test
    void evaluate() {
        var eval = new StayNightEvaluation(Sport.HIKING, Duration.ofHours(3));
        var path = GetTracks.getMountainTrack().getOrderedLocations();
        Detours detours = new Detours();
        detours.addDetour(
                new Detour(
                        1,
                        new WayPoint(2,2,2,"place to stay night"),
                        Duration.ofHours(1)
                )
        );
        var expDirect = eval.evaluate(path,detours,new Representation(new boolean[]{false}));
        var expWithDet = eval.evaluate(path,detours,new Representation(new boolean[]{true}));
        Assertions.assertTrue(expDirect > expWithDet);
    }


}