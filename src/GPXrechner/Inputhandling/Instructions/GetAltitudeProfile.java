package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.Parsing.ConsoleParsing;
import GPXrechner.Inputhandling.States.State;
import GPXrechner.WayModel.Entities.ElevationProfile;
import GPXrechner.WayModel.Entities.Path;

public class GetAltitudeProfile implements Instruction{
    @Override
    public String getDescription() {
        return "see the altitude profile of the path";
    }
    @Override
    public State execute(State state) throws InvalidStateException {
        Path path;
        try {
            path = state.getPath();
        }catch (NullPointerException e){
            System.out.println("Need to load gpx before showing altitude profile");
            return state;
        }
        ElevationProfile elevationProfile;
        try {
            int granularity = ConsoleParsing.getGranularity();
            elevationProfile = new ElevationProfile(path,granularity);
        }catch (Exception e){
            elevationProfile = new ElevationProfile(path);
        }
        System.out.println(elevationProfile);
        return state;
    }

    @Override
    public String getRegex() {
        return "show altitude profile";
    }
}
