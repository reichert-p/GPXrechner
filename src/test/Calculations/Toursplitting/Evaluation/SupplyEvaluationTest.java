package test.Calculations.Toursplitting.Evaluation;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TourSplitting.Detours;
import GPXrechner.Calculations.TourSplitting.Evaluation.StayNightEvaluation;
import GPXrechner.Calculations.TourSplitting.Representation;
import GPXrechner.Interfaces.Parsing.DOMParser;
import GPXrechner.Interfaces.Parsing.NoTrackException;
import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.Location;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SupplyEvaluationTest {

    @Test
    void getDescription() {
    }

    @Test
    void getRegex() {
    }

    @Test
    void evaluate() throws NoTrackException {
        MovementSpeed movementSpeed = Sport.HIKING;
        Duration maxDailyMovementTime = Duration.ofHours(8);
        StayNightEvaluation stayNightEvaluation = new StayNightEvaluation(movementSpeed, maxDailyMovementTime);

        DOMParser domParser = new DOMParser();
        Path path = domParser.parseTrack("Files/GPX/Track/Watzmann.gpx") ;
        Detours detours = new Detours();
        /* detours.addDetour();
        Representation representation = new Representation(5);

        long result = stayNightEvaluation.evaluate(path, detours, representation);

        assertEquals(8, result);*/
    }
}