package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.TimePrediction;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;
import GPXrechner.WayModel.Entities.Path;

public class PredictTime implements Instruction{
    @Override
    public String getDescription() {
        return "predict the time needed for the path";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (state instanceof TourLoaded || state instanceof TrackLoaded){
            Path path = state.getPath();
            MovementSpeed movementSpeed = ConsoleParsing.parseMovementSpeed();
            ConsoleInformation.infoEstimatedTime(path.toString(),TimePrediction.predictTime(path,movementSpeed));
            return state;
        }
        throw new InvalidStateException("Tour loaded, Track loaded", state);
    }

    @Override
    public String getRegex() {
        return "predict time";
    }
}
