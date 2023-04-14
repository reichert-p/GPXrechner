package test.Calculations.Toursplitting.Evaluation;

import GPXrechner.Domain.Calculations.MovementSpeed.Sport;
import GPXrechner.Domain.Calculations.TourSplitting.Detour;
import GPXrechner.Domain.Calculations.TourSplitting.Detours;
import GPXrechner.Domain.Calculations.TourSplitting.Evaluation.StayNightEvaluation;
import GPXrechner.Domain.Calculations.TourSplitting.Representation;
import GPXrechner.Domain.WayModel.WayPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.GetTracks;

import java.time.Duration;

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