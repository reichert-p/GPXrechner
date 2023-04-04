package test.Calculations.Toursplitting;

import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Calculations.TourSplitting.Evaluation.SupplyEvaluation;
import GPXrechner.Calculations.TourSplitting.Hillclimbing;
import GPXrechner.Calculations.TourSplitting.Representation;
import GPXrechner.Calculations.TourSplitting.WayPointSet;
import GPXrechner.Interfaces.Parsing.GPXReader.GPXToTrack;
import GPXrechner.Interfaces.Parsing.GPXReader.GPXToWayPointSet;
import GPXrechner.Interfaces.Parsing.GPXReader.NoDataException;
import GPXrechner.WayModel.Entities.Track;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class HillClimbingTest {
    @Test
    void evaluationFunction() throws NoDataException {
        EvaluationFunction evaluationFunction = new EvaluationFunctionMock();
        GPXToTrack trackParser = new GPXToTrack();
        trackParser.read("Files\\GPX\\Track\\Watzmann.gpx");
        Track watzmannTrack = trackParser.getTrack();

        GPXToWayPointSet wayPointParser = new GPXToWayPointSet();
        wayPointParser.read("Files\\GPX\\Waypoints\\WatzmannWasser.gpx");
        WayPointSet wasser = wayPointParser.getWayPointSet();

        Hillclimbing evo = new Hillclimbing(watzmannTrack,wasser, Sport.HIKING,evaluationFunction);

        var best = evo.getBestRepresentation().getWayPoints();
        Assertions.assertEquals(2, best.size());
        Assertions.assertEquals("Watzmannhaus", best.get(0).toString());
        Assertions.assertEquals("Quelle Schönfeld", best.get(1).toString());
    }
}