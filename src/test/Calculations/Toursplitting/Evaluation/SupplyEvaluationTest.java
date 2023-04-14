package test.Calculations.Toursplitting.Evaluation;

import GPXrechner.Domain.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Domain.Calculations.MovementSpeed.Sport;
import GPXrechner.Domain.Calculations.TourSplitting.Detours;
import GPXrechner.Domain.Calculations.TourSplitting.Evaluation.StayNightEvaluation;
import GPXrechner.Plugin.Parsing.GPXReader.GPXToTrack;
import GPXrechner.Plugin.Parsing.GPXReader.NoDataException;
import GPXrechner.Domain.WayModel.Entities.Path;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class SupplyEvaluationTest {

    @Test
    void evaluate() throws NoDataException {
        MovementSpeed movementSpeed = Sport.HIKING;
        Duration maxDailyMovementTime = Duration.ofHours(8);
        StayNightEvaluation stayNightEvaluation = new StayNightEvaluation(movementSpeed, maxDailyMovementTime);

        GPXToTrack trackParser = new GPXToTrack();
        trackParser.read("Files/GPX/Track/Watzmann.gpx") ;
        Path path = trackParser.getTrack();
        Detours detours = new Detours();
        /* detours.addDetour();
        Representation representation = new Representation(5);

        long result = stayNightEvaluation.evaluate(path, detours, representation);

        assertEquals(8, result);*/
    }
}