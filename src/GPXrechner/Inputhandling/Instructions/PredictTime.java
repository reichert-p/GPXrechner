package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.States.State;

public class PredictTime implements Instruction{
    @Override
    public String getDescription() {
        return "predict the time needed for the path";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        return null;
    }

    @Override
    public String getRegex() {
        return null;
    }
}
