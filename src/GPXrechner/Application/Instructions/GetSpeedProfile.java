package GPXrechner.Application.Instructions;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Interfaces.InvalidStateException;
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
            throw new InvalidStateException(this,state);
        }
        Tour tour;
        try {
            tour = (Tour)state.getPath();
        }catch (Exception e){
            System.out.println("this should not happen :))))");
            return state;
        }
        SpeedProfile speedProfile;
        try {
            int granularity = ConsoleParsing.getGranularity();
            speedProfile = new SpeedProfile(tour,granularity);
            System.out.println(speedProfile);
        }catch (InsufficientDataException e) {
            System.out.println("granularity too high for amount of Locations in Path");;
        }catch (Exception e){
            try {
                speedProfile = new SpeedProfile(tour);
                System.out.println(speedProfile);
            } catch (InsufficientDataException ex) {
                System.out.println("invalid input for granularity. standard granularity of 10 is too high for amount of Locations in path");
            }
        }

        return state;
    }

    @Override
    public String getRegex() {
        return "show speed profile";
    }
}
