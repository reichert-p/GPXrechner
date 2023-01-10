package GPXrechner.Interfaces;

import GPXrechner.Application.Instructions.Instruction;
import GPXrechner.Application.States.State;

public class InvalidStateException extends Exception {
    public InvalidStateException(Instruction instruction, State state){
        super(instruction.getDescription() + " in state " + state.toString());
    }
}
