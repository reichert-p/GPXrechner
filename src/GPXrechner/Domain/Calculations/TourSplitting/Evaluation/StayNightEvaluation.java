package GPXrechner.Domain.Calculations.TourSplitting.Evaluation;

import GPXrechner.Domain.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Domain.Calculations.MovementSpeed.Sport;
import GPXrechner.Domain.Calculations.TimePrediction;
import GPXrechner.Domain.Calculations.TourSplitting.Detours;
import GPXrechner.Domain.Calculations.TourSplitting.Representation;
import GPXrechner.Domain.WayModel.Location;

import java.time.Duration;
import java.util.ArrayList;

/**
 * This Evaluation is for optimizing the total amount of Days needed for a Track
 */
public class StayNightEvaluation implements EvaluationFunction {
    private final MovementSpeed movementSpeed;
    private final Duration maxTimeBetweenWaypoints;

    /**
     * @param movementSpeed        the expected Speed in which the tour is taken
     * @param maxDailyMovementTime the rough time one expects to move each day (not necessarily the absolute maximum)
     */
    public StayNightEvaluation(MovementSpeed movementSpeed, Duration maxDailyMovementTime) {
        this.movementSpeed = movementSpeed;
        this.maxTimeBetweenWaypoints = maxDailyMovementTime;
    }

    @Override
    public long evaluate(ArrayList<Location> path, Detours detours, Representation representation) {
        int days = 1;
        for (int i = 0; i < detours.getPossibleDetours().size(); i++) {
            if (representation.getBitString()[i]) {
                days++;
            }
        }
        double overShoot = getWeightedOvershoot(path, detours, representation);
        return (long) (maxTimeBetweenWaypoints.toHours() * days + overShoot * 12); //when 20% additional time an extra day pays off
    }

    @Override
    public String getDescription() {
        return "evaluates the total amount of Days needed for a Track";
    }

    @Override
    public String getRegex() {
        return "stay night eval";
    }

    /**
     * overshoot in minutes, quadratic weight for high overshoot
     */
    private double getWeightedOvershoot(ArrayList<Location> path, Detours detours, Representation representation) {
        Duration sum = Duration.ZERO;
        var orderedVisitedDetours = EvaluationHelper.getRepresentedDetoursOrdered(detours, representation);
        if (orderedVisitedDetours.isEmpty()) {
            sum = TimePrediction.predictTime(path, this.movementSpeed);
        } else {
            Duration firstSectionDuration = TimePrediction.predictTime(path.subList(0, orderedVisitedDetours.get(0).getPosition()), this.movementSpeed);
            firstSectionDuration = firstSectionDuration.plus(orderedVisitedDetours.get(0).getExpenditure());
            sum = weightedAdd(sum, firstSectionDuration);
            for (int i = 0; i < orderedVisitedDetours.size() - 1; i++) {
                Duration sectionDuration = TimePrediction.predictTime(path.subList(orderedVisitedDetours.get(i).getPosition(), orderedVisitedDetours.get(i + 1).getPosition()), Sport.HIKING);
                sectionDuration = sectionDuration.plus(orderedVisitedDetours.get(i).getExpenditure());
                sectionDuration = sectionDuration.plus(orderedVisitedDetours.get(i + 1).getExpenditure());
                sum = weightedAdd(sum, sectionDuration);
            }
            Duration lastSectionDuration = TimePrediction.predictTime(path.subList(orderedVisitedDetours.get(orderedVisitedDetours.size() - 1).getPosition(), path.size() - 1), this.movementSpeed);
            lastSectionDuration = lastSectionDuration.plus(orderedVisitedDetours.get(orderedVisitedDetours.size() - 1).getExpenditure());
            sum = weightedAdd(sum, lastSectionDuration);
        }
        return sum.toMinutes();
    }

    private Duration weightedAdd(Duration base, Duration summand) {
        Duration overshoot = summand.minus(maxTimeBetweenWaypoints);
        if (!overshoot.isPositive()) {
            return base;
        }
        Duration weightedOvershoot = overshoot.multipliedBy(overshoot.toHours()); //quadratic
        return base.plus(weightedOvershoot);
    }
}
