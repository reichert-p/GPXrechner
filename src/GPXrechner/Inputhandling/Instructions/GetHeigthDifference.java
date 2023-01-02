package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.States.State;

public class GetHeigthDifference implements Instruction{
    @Override
    public String getDescription() {
        return "see the height difference of the path";
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
