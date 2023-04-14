package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Domain.Calculations.InsufficientDataException;
import GPXrechner.Domain.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Domain.Calculations.SpeedCalculator;
import GPXrechner.Domain.WayModel.Entities.Tour;
import GPXrechner.Plugin.InvalidStateException;
import GPXrechner.Application.UserOutput;

public class GetPMS implements Instruction {

    UserOutput userOutput;

    public GetPMS(UserOutput consoleInformation) {
        this.userOutput = consoleInformation;
    }

    @Override
    public String getDescription() {
        return "see the personal movement speed of the tour";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (!(state instanceof TourLoaded)) {
            throw new InvalidStateException("TourLoaded", state);
        }
        Tour tour = (Tour) state.getPath();
        try {
            MovementSpeed pms = SpeedCalculator.predictPersonalMovementSpeed(tour);
            userOutput.infoPMSofTour(tour.toString(), pms);
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public String getRegex() {
        return "show tour movement speed";
    }
}
