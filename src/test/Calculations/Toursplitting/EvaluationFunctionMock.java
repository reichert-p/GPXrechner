package test.Calculations.Toursplitting;

import GPXrechner.Domain.Calculations.TourSplitting.Detours;
import GPXrechner.Domain.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Domain.Calculations.TourSplitting.Representation;
import GPXrechner.Domain.WayModel.WayModel.Location;

import java.util.ArrayList;

public class EvaluationFunctionMock implements EvaluationFunction {
    @Override
    public long evaluate(ArrayList<Location> path, Detours detours, Representation representation) {
        var bitstring = representation.getBitString();
        if (bitstring[0] && bitstring[1] && !bitstring[2]) {
            return 1;
        }
        return 3;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getRegex() {
        return null;
    }
}
