package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.States.State;
import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.Units.Distance;

public class GetDistance implements Instruction{
    @Override
    public String getDescription() {
        return "see the distance of the path";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        Path path;
        try {
            path = state.getPath();
        }catch (NullPointerException e){
            System.out.println("gpx track needs to be loaded to get it's distance");
            return state;
        }
        System.out.println("Length of Path " + path.toString() + ": " + DistanceCalculator.calc3dDistance(path));
        return state;
    }

    @Override
    public String getRegex() {
        return "show distance";
    }
}
