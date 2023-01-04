package GPXrechner.WayModel.Entities;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.WayModel.TourPoint;

import java.util.ArrayList;
import java.util.List;

public class SpeedProfile {
    private static final int yGranularity = 10;
    private boolean[][] profile;
    public SpeedProfile(Tour tour, int xGranularity) throws InsufficientDataException {
        this.profile = calculateSpeedProfile(tour,xGranularity);
    }

    public SpeedProfile(Tour tour) throws InsufficientDataException {
        new SpeedProfile(tour,10);
    }
    private boolean[][] calculateSpeedProfile(Tour tour, int xGranularity) throws InsufficientDataException {
        List<TourPoint> tourPoints = tour.getOrderedLocations().stream()
                .filter(c -> c instanceof TourPoint)
                .map(c -> (TourPoint)c)
                .toList(); //massive casting
        boolean[][] output = new boolean[xGranularity][yGranularity];
        int[] sectionLength = ProfileCalculation.split(tourPoints.size() , xGranularity);
        List<Double> speeds = new ArrayList<>();
        int processed = 0;
        for (int i = 0; i < xGranularity; i++){
                speeds.add(SpeedCalculator.calculateSpeedDeviation(tour, tourPoints.subList(processed,processed+sectionLength[i])));
            processed += sectionLength[i];
        }
        speeds = ProfileCalculation.normalize(speeds);
        for (int i = 0; i < xGranularity;i++) {
            for (int j = 0; j < yGranularity; j++) {
                if (speeds.get(i)*yGranularity >= j){
                    output[i][j] = true;
                }
            }
        }
        return output;
    }

    public boolean[][] getProfile() {
        return profile;
    }
}
