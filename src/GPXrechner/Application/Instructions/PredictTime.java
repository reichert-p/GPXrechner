package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Domain.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Domain.Calculations.TimePrediction;
import GPXrechner.Domain.WayModel.WayModel.Entities.Path;
import GPXrechner.Plugin.InvalidStateException;
import GPXrechner.Application.UserOutput;
import GPXrechner.Application.UserInput;

public class PredictTime implements Instruction {
    UserOutput userOutput;
    UserInput userInput;

    public PredictTime(UserOutput userOutput, UserInput userInput) {
        this.userOutput = userOutput;
        this.userInput = userInput;
    }

    @Override
    public String getDescription() {
        return "predict the time needed for the path";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (state instanceof TourLoaded || state instanceof TrackLoaded) {
            Path path = state.getPath();
            MovementSpeed movementSpeed = userInput.parseMovementSpeed();
            userOutput.infoEstimatedTime(path.toString(), TimePrediction.predictTime(path, movementSpeed));
            return state;
        }
        throw new InvalidStateException("Tour loaded, Track loaded", state);
    }

    @Override
    public String getRegex() {
        return "predict time";
    }
}
