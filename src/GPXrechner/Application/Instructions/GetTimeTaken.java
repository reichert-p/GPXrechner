package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Domain.Calculations.SpeedHeuristics;
import GPXrechner.Domain.WayModel.Entities.Tour;
import GPXrechner.Plugin.InvalidStateException;
import GPXrechner.Application.UserOutput;

public class GetTimeTaken implements Instruction {

    UserOutput userOutput;

    public GetTimeTaken(UserOutput consoleInformation) {
        this.userOutput = consoleInformation;
    }

    @Override
    public String getDescription() {
        return "see the time taken for a Tour";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (state instanceof TourLoaded) {
            Tour tour = (Tour) state.getPath();
            userOutput.infoTimeTaken(tour.toString(), SpeedHeuristics.calculateTime(tour));
        }
        throw new InvalidStateException("TourLoaded", state);
    }

    @Override
    public String getRegex() {
        return "see time taken";
    }
}
