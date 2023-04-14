package GPXrechner.Domain.Calculations.TourSplitting.Evaluation;

import GPXrechner.Domain.Calculations.TourSplitting.Detours;
import GPXrechner.Domain.Calculations.TourSplitting.Representation;
import GPXrechner.Domain.WayModel.Location;

import java.util.ArrayList;

public interface EvaluationFunction {
    long evaluate(ArrayList<Location> path, Detours detours, Representation representation);

    String getDescription();

    String getRegex();
}
