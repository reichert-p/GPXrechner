package GPXrechner.Inputhandling;

import GPXrechner.Inputhandling.Instructions.Instruction;
import GPXrechner.Inputhandling.States.State;

public class InvalidStateException extends Exception {
    public InvalidStateException(Instruction instruction, State state){
        super(instruction.getDescription() + " in state " + state.toString());
    }
}
