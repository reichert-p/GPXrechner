package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.WayModel.Entities.Tour;

public class GetPMS implements Instruction{
    @Override
    public String getDescription() {
        return "see the personal movement speed of the tour";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (!(state instanceof TourLoaded)){
            throw new InvalidStateException("TourLoaded",state);
        }
        Tour tour = (Tour)state.getPath();
        try {
            MovementSpeed pms = SpeedCalculator.predictPersonalMovementSpeed(tour);
            ConsoleInformation.infoPMSofTour(tour.toString(),pms);
        }catch (InsufficientDataException e){
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public String getRegex() {
        return "show tour movement speed";
    }
}
