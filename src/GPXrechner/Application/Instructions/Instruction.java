package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Interfaces.InvalidStateException;

public interface Instruction {
    public String getDescription();

    public State execute(State state) throws InvalidStateException;

    public String getRegex();

}
