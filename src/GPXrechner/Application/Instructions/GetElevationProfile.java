package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;
import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.Profiles.ElevationProfile;

public class GetElevationProfile implements Instruction{
    @Override
    public String getDescription() {
        return "see the elevation profile of the path";
    }
    @Override
    public State execute(State state) throws InvalidStateException {
        if (state instanceof TourLoaded || state instanceof TrackLoaded){
            Path path = state.getPath();
            ElevationProfile elevationProfile;
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
                elevationProfile = new ElevationProfile(path,granularity);
                ConsoleInformation.showElevationProfile(path.toString(), elevationProfile);
            }catch (InsufficientDataException e) {
                ConsoleInformation.alertGranularityTooHigh(granularity);
            }
            return state;
        }
        throw new InvalidStateException("Tour loaded, Track loaded", state);
    }

    @Override
    public String getRegex() {
        return "show elevation profile";
    }
}
