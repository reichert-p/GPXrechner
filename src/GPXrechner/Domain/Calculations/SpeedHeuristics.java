package GPXrechner.Domain.Calculations;

import GPXrechner.Domain.WayModel.WayModel.Entities.Tour;
import GPXrechner.Domain.WayModel.WayModel.Location;
import GPXrechner.Domain.WayModel.WayModel.TourPoint;
import GPXrechner.Domain.WayModel.WayModel.Units.Pace;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SpeedHeuristics {
    private static Duration calculateTime(TourPoint a, TourPoint b) {
        return Duration.between(a.getTime(), b.getTime());
    }

    public static Duration calculateTime(Tour tour) {
        List<Location> locations = tour.getOrderedLocations();
        TourPoint first = (TourPoint) (locations.get(0));
        TourPoint last = (TourPoint) (locations.get(locations.size() - 1));
        return Duration.between(first.getTime(), last.getTime());
    }

    public static Pace getDescendingHeuristic(List<TourPoint> tourPoints, Pace horizontalHeuristic) throws InsufficientDataException {
        ArrayList<Pace> paces = new ArrayList<>();
        for (int i = 0; i < tourPoints.size() - 1; i++) { //finden der paces wos runna geht
            TourPoint a = tourPoints.get(i);
            TourPoint b = tourPoints.get(i + 1);
            if (DistanceCalculator.calcElevationGain(a, b).getDown() > 0) { // climbing between nodes
                Duration total = calculateTime(a, b);
                Duration horizontal = Duration.ofSeconds((long) (3600 * DistanceCalculator.calc2dDistance(a, b).getValue() / horizontalHeuristic.getValue()));
                Duration vertical;
                if (2 * horizontal.getSeconds() > total.getSeconds()) { //horizontal time predominant
                    vertical = Duration.ofSeconds(2 * total.getSeconds() - 2 * horizontal.getSeconds());
                } else { //vertical time predominant
                    vertical = Duration.ofSeconds(total.getSeconds() - (long) (0.5 * horizontal.getSeconds()));
                }
                double pace = DistanceCalculator.calcElevationGain(a, b).getDown() / (vertical.getSeconds() / 3600.0);
                if (pace > 0 && Double.isFinite(pace)) { //retardedes auf 0 divisor checken
                    paces.add(new Pace(pace));
                }
            }
        }
        return new Pace(percentileAndAverage(paces));
    }

    public static Pace getClimbingHeuristic(List<TourPoint> tourPoints, Pace horizontalHeuristic) throws InsufficientDataException {
        ArrayList<Pace> paces = new ArrayList<>();
        for (int i = 0; i < tourPoints.size() - 1; i++) { //finden der paces wos hoch geht
            TourPoint a = tourPoints.get(i);
            TourPoint b = tourPoints.get(i + 1);
            if (DistanceCalculator.calcElevationGain(a, b).getUp() > 0) { // climbing between nodes
                Duration total = calculateTime(a, b);
                double horizontalHours = DistanceCalculator.calc2dDistance(a, b).getValue() / horizontalHeuristic.getValue();
                Duration horizontal = Duration.ofSeconds((long) (3600 * horizontalHours));
                Duration vertical;
                if (2 * horizontal.getSeconds() > total.getSeconds()) { //horizontal time predominant
                    vertical = Duration.ofSeconds(2 * total.getSeconds() - 2 * horizontal.getSeconds());
                } else { //vertical time predominant
                    vertical = Duration.ofSeconds(total.getSeconds() - (long) (0.5 * horizontal.getSeconds()));
                }
                double pace = DistanceCalculator.calcElevationGain(a, b).getUp() / (vertical.getSeconds() / 3600.0);
                if (pace > 0 && Double.isFinite(pace)) { //retardedes auf 0 divisor checken
                    paces.add(new Pace(pace));
                }
            }
        }
        return new Pace(percentileAndAverage(paces));
    }

    /**
     * take most horizontal passages, ignore vertical movement ignore 50% slowest and 10% fastest, average of the rest
     */
    public static Pace getHorizontalHeuristic(List<TourPoint> tourPoints) throws InsufficientDataException {
        ArrayList<Pace> paces = new ArrayList<>();
        for (int i = 0; i < tourPoints.size() - 1; i++) { //finden der paces wos flach ist
            TourPoint a = tourPoints.get(i);
            TourPoint b = tourPoints.get(i + 1);
            if (Math.abs(DistanceCalculator.calcElevationGain(a, b).getManhattenNorm() / DistanceCalculator.calc2dDistance(a, b).getValue()) < 0.05) { //5% > Inclination
                paces.add(new Pace(DistanceCalculator.calc2dDistance(a, b).getValue() / calculateTime(a, b).getSeconds() * 3600));
            }
        }
        return new Pace(percentileAndAverage(paces));
    }

    /**
     * generates the average of an array of Paces. Runaways are considered
     */
    private static double percentileAndAverage(ArrayList<Pace> list) throws InsufficientDataException {
        if (list == null || list.isEmpty()) {
            throw new InsufficientDataException();
        }
        List<Double> paceValues = list.stream()
                .map(Pace::getValue)
                .sorted()
                .toList();
        var consideredPaceValues = removeRunawaysfromSortedList(paceValues, 0.25, 0.9);
        double sum = consideredPaceValues.stream().mapToDouble(f -> f).sum();
        double instances = consideredPaceValues.size();
        return sum / instances;
    }

    private static List<Double> removeRunawaysfromSortedList(List<Double> input, double lowerBound, double upperBound) {
        if (lowerBound < 0 || lowerBound > 1 || upperBound < 0 || upperBound > 1) {
            throw new RuntimeException("Bounds in removeRunaways should be a value between 0 and 1");
        }
        int length = input.size();
        return input.subList((int) (length * lowerBound), (int) Math.ceil(length * upperBound));
    }
}
