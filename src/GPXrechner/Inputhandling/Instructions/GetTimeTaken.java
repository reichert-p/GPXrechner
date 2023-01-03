package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Calculations.SpeedHeuristics;
import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.States.State;
import GPXrechner.Inputhandling.States.TourLoaded;
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
