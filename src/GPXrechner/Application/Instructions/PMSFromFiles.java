package GPXrechner.Application.Instructions;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;
import GPXrechner.Application.States.State;

public class PMSFromFiles implements  Instruction{
    @Override
    public String getDescription() {
        return "calculate a personal movement speed from tour paths";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        try {
            System.out.println(ConsoleParsing.pathsToMovementSpeeds());
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public String getRegex() {
        return "generate personal movement speed";
    }
}
