package GPXrechner.Application.Instructions;

import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Application.States.State;
import GPXrechner.WayModel.Entities.Path;

public class GetHeigthDifference implements Instruction{
    @Override
    public String getDescription() {
        return "see the height difference of the path";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        Path path;
        try {
            path = state.getPath();
        }catch (NullPointerException e){
            System.out.println("gpx track needs to be loaded to get it's height difference");
            return state;
        }
        System.out.println("Elevation difference of Path " + path.toString() + ": " + DistanceCalculator.calcElevationGain(path));
        return state;
    }

    @Override
    public String getRegex() {
        return "show height difference";
    }
}
