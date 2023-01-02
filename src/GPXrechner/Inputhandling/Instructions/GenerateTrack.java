package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.States.State;

public class GenerateTrack implements Instruction {
    @Override
    public String getDescription() {
        return "generate a track from the tour";
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
