package GPXrechner.Application.Instructions;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
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
        try {
            System.out.println("PMS of Tour " + tour.toString() + ": " + SpeedCalculator.predictPersonalMovementSpeed(tour));
        }catch (InsufficientDataException e){
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public String getRegex() {
        return "show personal movement speed"; //personal? more like specific
    }
}
