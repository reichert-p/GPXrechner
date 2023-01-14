package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;

public class PMSFromFiles implements  Instruction{
    @Override
    public String getDescription() {
        return "calculate a personal movement speed from tour paths";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        try {
            ConsoleInformation.infoPMS(ConsoleParsing.pathsToMovementSpeeds());
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
