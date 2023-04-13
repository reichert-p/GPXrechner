package GPXrechner.Interfaces.Parsing;

import GPXrechner.Application.Instructions.Instruction;
import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.Interfaces.Parsing.GPXReader.GPXToTour;
import GPXrechner.Interfaces.Parsing.GPXReader.NoDataException;
import GPXrechner.WayModel.Entities.Tour;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface UserInput {
    public String readPath();
    public String[] readPaths();
    public Instruction getInstruction(Instruction[] instructions);
    public int getGranularity();
    public MovementSpeed parseMovementSpeed();
    public MovementSpeed pathsToMovementSpeeds() throws InsufficientDataException;
    public EvaluationFunction parseEvaluationFunction(EvaluationFunction[] evaluationFunctions);
    public Duration parseMaxDuration();
}
