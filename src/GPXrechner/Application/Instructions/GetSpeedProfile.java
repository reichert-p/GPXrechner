package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Domain.Calculations.InsufficientDataException;
import GPXrechner.Domain.WayModel.Entities.Tour;
import GPXrechner.Domain.WayModel.Profiles.SpeedProfile;
import GPXrechner.Plugin.InvalidStateException;
import GPXrechner.Application.UserOutput;
import GPXrechner.Application.UserInput;

public class GetSpeedProfile implements Instruction {

    UserOutput userOutput;
    UserInput userInput;

    public GetSpeedProfile(UserOutput userOutput, UserInput userInput) {
        this.userOutput = userOutput;
        this.userInput = userInput;
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
        SpeedProfile speedProfile;
        int granularity = -1;
        try {
            granularity = userInput.getGranularity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (granularity < 1) {
            granularity = 10;
        }
        try {
            speedProfile = new SpeedProfile(tour, granularity);
            userOutput.showSpeedProfile(tour.toString(), speedProfile);
        } catch (InsufficientDataException e) {
            userOutput.alertGranularityTooHigh(granularity);
        }
        return state;
    }

    @Override
    public String getRegex() {
        return "show speed profile";
    }
}
