package GPXrechner.Application.Instructions;

import GPXrechner.Calculations.SpeedHeuristics;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
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
            System.out.println(SpeedHeuristics.calculateTime(tour));
        }
        return state;
    }

    @Override
    public String getRegex() {
        return "see time taken";
    }
}
