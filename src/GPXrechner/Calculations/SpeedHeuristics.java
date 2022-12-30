package GPXrechner.Calculations;

import GPXrechner.WayModel.TourPoint;
import GPXrechner.WayModel.Units.Pace;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SpeedHeuristics {
    private static Duration calculateTime(TourPoint a, TourPoint b){
        long timeDiff = b.getTime().getTime() - a.getTime().getTime();
        return Duration.ofMillis(timeDiff);
    }
    public static Pace getDescendingHeuristic(List<TourPoint> tourPoints, Pace horizontalHeuristic) {
        ArrayList<Pace> paces = new ArrayList<>();
        for (int i = 0; i < tourPoints.size() -1; i++) { //finden der paces wos runna geht
            TourPoint a = tourPoints.get(i);
            TourPoint b = tourPoints.get(i+1);
            if (DistanceCalculator.calcElevationGain(a,b).getDown() > 0){ // climbing between nodes
                Duration total = calculateTime(a,b);
                Duration horizontal = Duration.ofSeconds(3600*(long)(DistanceCalculator.calc2dDistance(a,b).getValue()/horizontalHeuristic.getValue()));
                Duration vertical;
                if (2*horizontal.getSeconds() > total.getSeconds()){ //horizontal time predominant
                    vertical = Duration.ofSeconds(2* total.getSeconds() - 2*horizontal.getSeconds());
                }
                else { //vertical time predominant
                    vertical = Duration.ofSeconds(total.getSeconds() - (long) (0.5 * horizontal.getSeconds()));
                }
                paces.add(new Pace(DistanceCalculator.calcElevationGain(a,b).getDown()/(vertical.getSeconds()/3600.0)));
            }
        }
        return new Pace(percentileAndAverage(paces));
    }

    public static Pace getClimbingHeuristic(List<TourPoint> tourPoints, Pace horizontalHeuristic) {
        ArrayList<Pace> paces = new ArrayList<>();
        for (int i = 0; i < tourPoints.size() -1; i++) { //finden der paces wos hoch geht
            TourPoint a = tourPoints.get(i);
            TourPoint b = tourPoints.get(i+1);
            if (DistanceCalculator.calcElevationGain(a,b).getUp() > 0){ // climbing between nodes
                Duration total = calculateTime(a,b);
                Duration horizontal = Duration.ofSeconds(3600*(long)(DistanceCalculator.calc2dDistance(a,b).getValue()/horizontalHeuristic.getValue()));
                Duration vertical;
                if (2*horizontal.getSeconds() > total.getSeconds()){ //horizontal time predominant
                    vertical = Duration.ofSeconds(2* total.getSeconds() - 2*horizontal.getSeconds());
                }
                else { //vertical time predominant
                    vertical = Duration.ofSeconds(total.getSeconds() - (long) (0.5 * horizontal.getSeconds()));
                }
                paces.add(new Pace(DistanceCalculator.calcElevationGain(a,b).getUp()/(vertical.getSeconds()/3600.0)));
            }
        }
        return new Pace(percentileAndAverage(paces));
    }

    /**
     * take most horizontal passages, ignore vertical movement ignore 50% slowest and 10% fastest, average of the rest
     */
    public static Pace getHorizontalHeuristic(List<TourPoint> tourPoints) {
        ArrayList<Pace> paces = new ArrayList<>();
        for (int i = 0; i < tourPoints.size() -1; i++) { //finden der paces wos flach ist
            TourPoint a = tourPoints.get(i);
            TourPoint b = tourPoints.get(i+1);
            if (Math.abs(DistanceCalculator.calcElevationGain(a,b).getManhattenNorm() / DistanceCalculator.calc2dDistance(a,b).getValue()) < 0.05){ //5% > Inclination
                paces.add(new Pace(DistanceCalculator.calc2dDistance(a,b).getValue() / calculateTime(a,b).getSeconds() * 3600));
            }
        }
        return new Pace(percentileAndAverage(paces));
    }

    /**
     * generates the average of an array of Paces. Runaways are considered
     */
    private static double percentileAndAverage(ArrayList<Pace> list){
        if (list==null){
            throw new RuntimeException("Data insufficient to calculate speed values");
        }
        List<Double> paceValues= list.stream()
                .map(Pace::getValue)
                .sorted()
                .toList();
        int i = 0;
        double sum = 0;
        double instances = 0;
        for (double d:paceValues) {
            if (i > paceValues.size() * 0.25 && i < paceValues.size() * 0.9) // remove runaways
                sum += d;
            instances ++;
            i++;
        }
        return sum/instances;
    }
}
