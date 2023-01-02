package GPXrechner.Inputhandling.States;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Entities.Path;
import GPXrechner.Inputhandling.Instructions.Instruction;

public interface State {
    public Path getPath();
}
