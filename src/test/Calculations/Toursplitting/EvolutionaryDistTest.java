package test.Calculations.Toursplitting;

import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Calculations.TourSplitting.Evaluation.SupplyEvaluation;
import GPXrechner.Calculations.TourSplitting.Hillclimbing;
import GPXrechner.Calculations.TourSplitting.NoWayPointsExeption;
import GPXrechner.Calculations.TourSplitting.Representation;
import GPXrechner.Calculations.TourSplitting.WayPointSet;
import GPXrechner.Interfaces.Parsing.GPXReader.GPXToTrack;
import GPXrechner.Interfaces.Parsing.GPXReader.GPXToWayPointSet;
import GPXrechner.Interfaces.Parsing.GPXReader.NoDataException;
import GPXrechner.Interfaces.Parsing.NoTrackException;
import GPXrechner.WayModel.Entities.Track;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class EvolutionaryDistTest {
    @Test
    void evaluationFunction() throws NoDataException {
        EvaluationFunction evaluationFunction = new SupplyEvaluation(Sport.HIKING, Duration.ofHours(5));
        GPXToTrack trackParser = new GPXToTrack();
        trackParser.read("Files\\GPX\\Track\\Watzmann.gpx");
        Track watzmannTrack = trackParser.getTrack();

        GPXToWayPointSet wayPointParser = new GPXToWayPointSet();
        wayPointParser.read("Files\\GPX\\Waypoints\\WatzmannWasser.gpx");
        WayPointSet wasser = wayPointParser.getWayPointSet();

        Hillclimbing evo = new Hillclimbing(watzmannTrack,wasser, Sport.HIKING,evaluationFunction);
        Assertions.assertEquals(135900,evo.evaluationFunction(new Representation(new boolean[]{true,true,true})));
        Assertions.assertEquals(135849 ,evo.evaluationFunction(new Representation(new boolean[]{true,true,false})));
        Assertions.assertEquals(196117 ,evo.evaluationFunction(new Representation(new boolean[]{true,false,true})));
        Assertions.assertEquals( 514764,evo.evaluationFunction(new Representation(new boolean[]{true,false,false})));
        Assertions.assertEquals( 561740,evo.evaluationFunction(new Representation(new boolean[]{false,true,true})));
        Assertions.assertEquals(561527 , evo.evaluationFunction(new Representation(new boolean[]{false,true,false})));
        Assertions.assertEquals(620478 , evo.evaluationFunction(new Representation(new boolean[]{false,false,true})));
        Assertions.assertEquals( 936051,evo.evaluationFunction(new Representation(new boolean[]{false,false,false})));
        var best = evo.getBestRepresentation().getWayPoints(); //this should statistically work over 99.999%
        Assertions.assertEquals(2, best.size());
        Assertions.assertEquals("Watzmannhaus", best.get(0).toString());
        Assertions.assertEquals("Quelle Schönfeld", best.get(1).toString());
    }
}