package GPXrechner.Application.Instructions;

import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Application.States.State;

public interface Instruction {
    public String getDescription();

    public State execute(State state) throws InvalidStateException;

    public String getRegex(); //TODO all out's in extra Class to split application and Plugins

}
