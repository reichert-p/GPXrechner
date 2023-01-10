package GPXrechner.Application.Instructions;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;
import GPXrechner.Application.States.State;
import GPXrechner.WayModel.Profiles.ElevationProfile;
import GPXrechner.WayModel.Entities.Path;

public class GetElevationProfile implements Instruction{
    @Override
    public String getDescription() {
        return "see the elevation profile of the path";
    }
    @Override
    public State execute(State state) throws InvalidStateException {
        Path path;
        try {
            path = state.getPath();
        }catch (NullPointerException e){
            System.out.println("Need to load gpx before showing elevation profile");
            return state;
        }
        ElevationProfile elevationProfile;
        try {
            int granularity = ConsoleParsing.getGranularity();
            elevationProfile = new ElevationProfile(path,granularity);
            System.out.println(elevationProfile);
        }catch (Exception | InsufficientDataException e){
            try {
                elevationProfile = new ElevationProfile(path);
                System.out.println(elevationProfile);
            }catch (InsufficientDataException f){
                f.printStackTrace();
            }
        }
        return state;
    }

    @Override
    public String getRegex() {
        return "show elevation profile";
    }
}
