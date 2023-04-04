package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Calculations.TourSplitting.Evaluation.StayNightEvaluation;
import GPXrechner.Calculations.TourSplitting.Evaluation.SupplyEvaluation;
import GPXrechner.Calculations.TourSplitting.Hillclimbing;
import GPXrechner.Calculations.TourSplitting.WayPointSet;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;
import GPXrechner.Interfaces.Parsing.GPXReader.GPXToWayPointSet;
import GPXrechner.Interfaces.Parsing.GPXReader.NoDataException;
import GPXrechner.WayModel.Entities.Path;

import java.time.Duration;

public class SplitTour implements Instruction{
    @Override
    public String getDescription() {
        return "split path depending on waypoints";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (!(state instanceof TourLoaded) && !(state instanceof TrackLoaded)){
            throw new InvalidStateException("Tour loaded, Track loaded", state);
        }
        Path path = state.getPath();
        WayPointSet wayPointSet = getWayPontSet();
        MovementSpeed movementSpeed = ConsoleParsing.parseMovementSpeed();
        Duration maxduration = ConsoleParsing.parseMaxDuration();
        EvaluationFunction evaluationFunction = ConsoleParsing.parseEvaluationFunction(generateEvaluationFunctions(movementSpeed,maxduration));
        Hillclimbing evo = new Hillclimbing(path,wayPointSet,movementSpeed,evaluationFunction);
        WayPointSet splitPoints = evo.getBestRepresentation();
        ConsoleInformation.infoWayPointsToSplit(splitPoints);
        return state;
    }

    private EvaluationFunction[] generateEvaluationFunctions(MovementSpeed movementSpeed, Duration maxDuration){ //allowed instructions are implemented here
        EvaluationFunction[] evaluationFunctions = new EvaluationFunction[]{
                new StayNightEvaluation(movementSpeed, maxDuration ),
                new SupplyEvaluation(movementSpeed, maxDuration)
        };
        return evaluationFunctions;
    }

    private WayPointSet getWayPontSet(){
        WayPointSet wayPointSet;
        String s = ConsoleParsing.readPath();
        try {
            GPXToWayPointSet wayPointParser = new GPXToWayPointSet();
            wayPointParser.read(s);
            wayPointSet = wayPointParser.getWayPointSet();
        } catch (NoDataException e) {
           ConsoleInformation.alertWrongFileType(s, "gpx path containing Waypoints");
           return getWayPontSet();
        }
        return wayPointSet;
    }

    @Override
    public String getRegex() {
        return "split path";
    }
}
