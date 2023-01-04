package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.Parsing.ConsoleParsing;
import GPXrechner.Inputhandling.Parsing.DOMParser;
import GPXrechner.Inputhandling.Parsing.NoTourException;
import GPXrechner.Inputhandling.Parsing.XMLParser;
import GPXrechner.Inputhandling.States.State;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.TourPoint;

import java.util.ArrayList;
import java.util.List;

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
