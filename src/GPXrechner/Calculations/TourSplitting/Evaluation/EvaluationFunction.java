package GPXrechner.Calculations.TourSplitting.Evaluation;

import GPXrechner.Calculations.TourSplitting.Detours;
import GPXrechner.Calculations.TourSplitting.Representation;
import GPXrechner.WayModel.Location;

import java.util.ArrayList;

public interface EvaluationFunction {
    long evaluate(ArrayList<Location> path, Detours detours, Representation representation);

    public String getDescription();

    public String getRegex();
}
