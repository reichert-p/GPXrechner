package GPXrechner.Application;

import GPXrechner.Application.Instructions.Instruction;
import GPXrechner.Domain.Calculations.InsufficientDataException;
import GPXrechner.Domain.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Domain.Calculations.TourSplitting.Evaluation.EvaluationFunction;

import java.time.Duration;

public interface UserInput {
    String readPath();

    String[] readPaths();

    Instruction getInstruction(Instruction[] instructions);

    int getGranularity();

    MovementSpeed parseMovementSpeed();

    MovementSpeed pathsToMovementSpeeds() throws InsufficientDataException;

    EvaluationFunction parseEvaluationFunction(EvaluationFunction[] evaluationFunctions);

    Duration parseMaxDuration();
}
