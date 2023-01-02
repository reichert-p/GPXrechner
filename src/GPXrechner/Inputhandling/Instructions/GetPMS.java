package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.States.State;

public class GetPMS implements Instruction{
    @Override
    public String getDescription() {
        return "see the personal movement speed of the tour";
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
