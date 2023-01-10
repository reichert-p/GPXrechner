package GPXrechner.Application.Instructions;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.TimePrediction;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;
import GPXrechner.Application.States.State;
import GPXrechner.WayModel.Entities.Path;

public class PredictTime implements Instruction{
    @Override
    public String getDescription() {
        return "predict the time needed for the path";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        Path path;
        try {
            path = state.getPath();
        }catch (NullPointerException e){
            System.out.println("gpx track needs to be loaded to predict a time");
            return state;
        }
        MovementSpeed movementSpeed = ConsoleParsing.parseMovementSpeed();
        System.out.println("Estimated Time for Path " + path.toString() + ": " + TimePrediction.predictTime(path,movementSpeed));
        return state;
    }

    @Override
    public String getRegex() {
        return "predict time";
    }
}
