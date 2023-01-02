package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.Parsing.ConsoleParsing;
import GPXrechner.Inputhandling.States.State;

public class PMSFromFiles implements  Instruction{
    @Override
    public String getDescription() {
        return "calculate a personal movement speed from tour paths";
    }
//TODO make sth to store/use PMS
    @Override
    public State execute(State state) throws InvalidStateException {
        String[] paths = ConsoleParsing.readPaths();
        System.out.println(SpeedCalculator.predictPersonalMovementSpeed(paths));
        return null;
    }

    @Override
    public String getRegex() {
        return "generate personal movement speed";
    }
}
