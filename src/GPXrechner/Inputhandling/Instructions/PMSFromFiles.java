package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.States.State;

public class PMSFromFiles implements  Instruction{
    @Override
    public String getDescription() {
        return "calculate a personal movement speed from tour paths";
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
