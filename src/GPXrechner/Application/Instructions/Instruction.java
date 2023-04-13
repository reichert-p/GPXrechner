package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Plugin.InvalidStateException;

public interface Instruction {
    String getDescription();

    State execute(State state) throws InvalidStateException;

    String getRegex();

}
