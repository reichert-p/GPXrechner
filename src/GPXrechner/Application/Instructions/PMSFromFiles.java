package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Domain.Calculations.InsufficientDataException;
import GPXrechner.Plugin.InvalidStateException;
import GPXrechner.Application.UserOutput;
import GPXrechner.Application.UserInput;

public class PMSFromFiles implements Instruction {
    UserOutput userOutput;
    UserInput userInput;

    public PMSFromFiles(UserOutput userOutput, UserInput userInput) {
        this.userOutput = userOutput;
        this.userInput = userInput;
    }

    @Override
    public String getDescription() {
        return "calculate a personal movement speed from tour paths";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        try {
            userOutput.infoPMS(userInput.pathsToMovementSpeeds());
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
