package GPXrechner.Calculations.TourSplitting;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.WayPoint;

import java.util.ArrayList;
import java.util.List;

public class Detours {

    List<Detour> possibleDetours;

    public Detours() {
        possibleDetours = new ArrayList<>();
    }

    public void addDetour(Detour detour) {
        possibleDetours.add(detour);
    }

    public List<Detour> getPossibleDetours() {
        return possibleDetours;
    }

    public static Detours initDetours(Path path, WayPointSet wayPointSet, MovementSpeed movementSpeed){
        Detours allPossibleDetours = new Detours();
        for (Location wp: wayPointSet.getWayPoints()) {
            allPossibleDetours.addDetour(
                Detour.createDetour(
                        path,
                        (WayPoint) wp,
                        new DirectWayHeuristic(movementSpeed)
                )
            );
        }
        return allPossibleDetours;
    }
}
