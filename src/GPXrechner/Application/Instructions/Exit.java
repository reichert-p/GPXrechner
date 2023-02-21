package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Interfaces.InvalidStateException;

public class Exit implements Instruction{
    @Override
    public String getDescription() {
        return "exit the program";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        System.exit(0);
        return state;
    }

    @Override
    public String getRegex() {
        return "exit";
    }
}
