package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.States.State;

public interface Instruction {
    public String getDescription();

    public State execute(State state) throws InvalidStateException;

    public String getRegex(); //TODO all out's in extra Class to split application and Plugins

}
