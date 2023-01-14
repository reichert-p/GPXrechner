package GPXrechner.Application.Instructions;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;
import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.WayModel.Profiles.SpeedProfile;
import GPXrechner.WayModel.Entities.Tour;

public class GetSpeedProfile implements Instruction{
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
        SpeedProfile speedProfile;
        int granularity = -1;
        try{
            granularity = ConsoleParsing.getGranularity();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (granularity < 1){
            granularity = 10;
        }
        try {
            speedProfile = new SpeedProfile(tour,granularity);
            ConsoleInformation.showSpeedProfile(tour.toString() , speedProfile);
        }catch (InsufficientDataException e) {
            ConsoleInformation.alertGranularityTooHigh(granularity);
        }
        return state;
    }

    @Override
    public String getRegex() {
        return "show speed profile";
    }
}
