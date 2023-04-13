package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Domain.Calculations.DistanceCalculator;
import GPXrechner.Domain.WayModel.WayModel.Entities.Path;
import GPXrechner.Plugin.InvalidStateException;
import GPXrechner.Application.UserOutput;

public class GetAltitudeDifference implements Instruction {
    UserOutput userOutput;

    public GetAltitudeDifference(UserOutput userOutput) {
        this.userOutput = userOutput;
    }

    @Override
    public String getDescription() {
        return "see the altitude difference of the path";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (state instanceof TourLoaded || state instanceof TrackLoaded) {
            Path path = state.getPath();
            userOutput.infoPathElevationGain(path.toString(), DistanceCalculator.calcElevationGain(path).toString());
            return state;
        }
        throw new InvalidStateException("Tour loaded, Track loaded", state);
    }

    @Override
    public String getRegex() {
        return "show altitude difference";
    }
}
