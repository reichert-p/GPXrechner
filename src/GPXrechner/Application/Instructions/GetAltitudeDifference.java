package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.WayModel.Entities.Path;

public class GetAltitudeDifference implements Instruction{
    @Override
    public String getDescription() {
        return "see the altitude difference of the path";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (state instanceof TourLoaded || state instanceof TrackLoaded){
            Path path = state.getPath();
            ConsoleInformation.infoPathElevationGain(path.toString(), DistanceCalculator.calcElevationGain(path).toString());
            return state;
        }
        throw new InvalidStateException("Tour loaded, Track loaded", state);
    }

    @Override
    public String getRegex() {
        return "show altitude difference";
    }
}
