package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Calculations.SpeedHeuristics;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.WayModel.Entities.Tour;

public class GetTimeTaken implements Instruction{
    @Override
    public String getDescription() {
        return "see the time taken for a Tour";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (state instanceof TourLoaded){
            Tour tour = (Tour)state.getPath();
            ConsoleInformation.infoTimeTaken(tour.toString(),SpeedHeuristics.calculateTime(tour));
        }
        throw new InvalidStateException("TourLoaded", state);
    }

    @Override
    public String getRegex() {
        return "see time taken";
    }
}
