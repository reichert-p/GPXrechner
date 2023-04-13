package test.Calculations.Toursplitting;

import GPXrechner.Domain.Calculations.MovementSpeed.Sport;
import GPXrechner.Domain.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Domain.Calculations.TourSplitting.Hillclimbing;
import GPXrechner.Domain.Calculations.TourSplitting.WayPointSet;
import GPXrechner.Plugin.Parsing.GPXReader.GPXToTrack;
import GPXrechner.Plugin.Parsing.GPXReader.GPXToWayPointSet;
import GPXrechner.Plugin.Parsing.GPXReader.NoDataException;
import GPXrechner.Domain.WayModel.WayModel.Entities.Track;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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