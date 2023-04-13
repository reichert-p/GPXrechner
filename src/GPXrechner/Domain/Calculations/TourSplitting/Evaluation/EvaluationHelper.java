package GPXrechner.Domain.Calculations.TourSplitting.Evaluation;

import GPXrechner.Domain.Calculations.TourSplitting.Detour;
import GPXrechner.Domain.Calculations.TourSplitting.Detours;
import GPXrechner.Domain.Calculations.TourSplitting.Representation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EvaluationHelper {
    public static List<Detour> getRepresentedDetoursOrdered(Detours detours, Representation representation) {
        List<Detour> visitedDetours = new ArrayList<>();
        for (int i = 0; i < detours.getPossibleDetours().size(); i++) {
            if (representation.getBitString()[i]) {
                visitedDetours.add(detours.getPossibleDetours().get(i));
            }
        }
        return visitedDetours.stream().sorted(Comparator.comparing(Detour::getPosition)).toList();
    }
}
