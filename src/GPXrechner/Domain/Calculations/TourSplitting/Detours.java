package GPXrechner.Domain.Calculations.TourSplitting;

import GPXrechner.Domain.WayModel.Entities.Path;
import GPXrechner.Domain.WayModel.Location;
import GPXrechner.Domain.WayModel.WayPoint;

import java.util.ArrayList;
import java.util.List;

public class Detours {

    List<Detour> possibleDetours;

    public Detours() {
        possibleDetours = new ArrayList<>();
    }

    public static Detours initDetours(Path path, WayPointSet wayPointSet, TimeHeuristic timeHeuristic) {
        Detours allPossibleDetours = new Detours();
        for (Location wp : wayPointSet.getWayPoints()) {
            allPossibleDetours.addDetour(
                    Detour.createDetour(
                            path,
                            (WayPoint) wp,
                            timeHeuristic
                    )
            );
        }
        return allPossibleDetours;
    }

    public void addDetour(Detour detour) {
        possibleDetours.add(detour);
    }

    public List<Detour> getPossibleDetours() {
        return possibleDetours;
    }
}
