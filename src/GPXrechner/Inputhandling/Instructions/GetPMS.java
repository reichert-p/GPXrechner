package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.States.State;
import GPXrechner.Inputhandling.States.TourLoaded;
import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.Entities.Tour;

public class GetPMS implements Instruction{
    @Override
    public String getDescription() {
        return "see the personal movement speed of the tour";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (!(state instanceof TourLoaded)){
            throw new InvalidStateException(this,state);
        }
        Tour tour;
        try {
            tour = (Tour)state.getPath();
        }catch (Exception e){
            System.out.println("this should not happen :))))");
            return state;
        }
        System.out.println("PMS of Tour " + tour.toString() + ": " + SpeedCalculator.predictPersonalMovementSpeed(tour));
        return state;
    }

    @Override
    public String getRegex() {
        return "show personal movement speed"; //personal? more like specific
    }
}
