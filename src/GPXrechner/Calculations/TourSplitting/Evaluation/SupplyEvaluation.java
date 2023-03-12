package GPXrechner.Calculations.TourSplitting.Evaluation;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TimePrediction;
import GPXrechner.Calculations.TourSplitting.Detours;
import GPXrechner.Calculations.TourSplitting.Representation;
import GPXrechner.WayModel.Location;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This Evaluation is for optimizing the necessary additional times used for gathering various resources (i.e. water, supermarket stuff, power)
 */
public class SupplyEvaluation implements EvaluationFunction {
    private final MovementSpeed movementSpeed;
    private final Duration maxTimeBetweenWaypoints;

    /**
     * @param movementSpeed the expected Speed in which the tour is taken
     * @param maxAutonomousTime the rough time one expects to move each day (not necessarily the absolute maximum)
     */
    public SupplyEvaluation(MovementSpeed movementSpeed, Duration maxAutonomousTime) {
        this.movementSpeed = movementSpeed;
        this.maxTimeBetweenWaypoints = maxAutonomousTime;
    }

    @Override
    public String getDescription() {
        return "optimizing the necessary additional time used for gathering various resources (i.e. water, supermarket stuff, power)";
    }

    @Override
    public String getRegex() {
        return "supply eval";
    }

    @Override
    public long evaluate(ArrayList<Location> path, Detours detours, Representation representation) {
        Duration sum = Duration.ZERO;
        Duration baseDuration = TimePrediction.predictTime(path, this.movementSpeed);
        for (int i = 0; i < detours.getPossibleDetours().size(); i++){
            if (representation.getBitString()[i]){
                sum = sum.plus(detours.getPossibleDetours().get(i).getExpenditure());
            }
        }
        sum = sum.plus(baseDuration);
        double overShoot = getOvershoot(path,detours,representation);
        if (overShoot > 1){
            overShoot -= 1;
            return (long)(sum.getSeconds() + sum.getSeconds() * 10 * overShoot);
        }
        return sum.getSeconds();
    }

    /**
     * Overshoot doesn't cumulate, it represents extra storage
     */
    private double getOvershoot(ArrayList<Location> path, Detours detours, Representation representation) {
        Duration max = Duration.ZERO;
        var orderedVisitedDetours = EvaluationHelper.getRepresentedDetoursOrdered(detours, representation);
        if(orderedVisitedDetours.isEmpty()){
            max = TimePrediction.predictTime(path, this.movementSpeed);
        }
        else {
            Duration firstSectionDuration = TimePrediction.predictTime(path.subList(0,orderedVisitedDetours.get(0).getPosition()),this.movementSpeed);
            firstSectionDuration = firstSectionDuration.plus(orderedVisitedDetours.get(0).getExpenditure());
            max = max(max, firstSectionDuration);
            for (int i = 0; i< orderedVisitedDetours.size()-1;i++){
                Duration sectionDuration = TimePrediction.predictTime(path.subList(orderedVisitedDetours.get(i).getPosition(),orderedVisitedDetours.get(i+1).getPosition()), Sport.HIKING);
                sectionDuration = sectionDuration.plus(orderedVisitedDetours.get(i).getExpenditure());
                sectionDuration = sectionDuration.plus(orderedVisitedDetours.get(i+1).getExpenditure());
                max = max(max,sectionDuration);
            }
            Duration lastSectionDuration = TimePrediction.predictTime(path.subList(orderedVisitedDetours.get(orderedVisitedDetours.size()-1).getPosition(), path.size()-1),this.movementSpeed);
            lastSectionDuration = lastSectionDuration.plus(orderedVisitedDetours.get(orderedVisitedDetours.size()-1).getExpenditure());
            max = max(max, lastSectionDuration);
        }
        return (double) (max.getSeconds())/(double) (maxTimeBetweenWaypoints.getSeconds());
    }

    private Duration max(Duration dur1, Duration dur2) {
        if (dur1.compareTo(dur2) >0 ){
            return dur1;
        }
        return dur2;
    }
}
