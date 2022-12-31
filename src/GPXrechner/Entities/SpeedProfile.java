package GPXrechner.Entities;

import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.TourPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpeedProfile {
    private static final int yGranularity = 10;
    private boolean[][] profile;
    double min,max;

    public SpeedProfile(Tour tour, int xGranularity) {
        this.profile = calculateSpeedProfile(tour,xGranularity);
    }

    public SpeedProfile(Tour tour){
        new ElevationProfile(tour,10);
    }
    //TODO put stuff together with ElevationProfile
    private boolean[][] calculateSpeedProfile(Tour tour, int xGranularity) {
        List<TourPoint> tourPoints = tour.getOrderedLocations().stream()
                .filter(c -> c instanceof TourPoint)
                .map(c -> (TourPoint)c)
                .toList(); //massive casting
        boolean[][] output = new boolean[xGranularity][yGranularity];

        int[] sectionLength = split(tourPoints.size() , xGranularity);
        List<Double> speeds = new ArrayList<>();
        int processed = 0;
        for (int i = 0; i < xGranularity; i++){
            speeds.add(SpeedCalculator.calculateSpeedDeviation(tour, tourPoints.subList(processed,processed+sectionLength[i])));
            processed += sectionLength[i];
        }
        this.max = Collections.max(speeds);
        this.min = Collections.min(speeds);
        speeds = normalize(speeds,min,max);
        for (int i = 0; i < xGranularity;i++) {
            for (int j = 0; j < yGranularity; j++) {
                if (speeds.get(i)*yGranularity >= j){
                    output[i][j] = true;
                }
            }
        }
        return output;
    }

    private List<Double> normalize(List<Double> list,double min, double max){
        double diff = max - min;
        for (int i = 0; i < list.size(); i++) {
            double val = list.get(i);
            double normalizedVal = (val - min) / diff;
            list.set(i,normalizedVal);
        }
        return list;
    }

    private int[] split(int pool,int sections){
        int[] output = new int[sections];
        int base = pool/sections;
        int remainder = pool % sections;
        for (int i = 0; i < output.length; i++){
            if (remainder <= i){
                output[i] = base;
            }
            if (remainder > i){
                output[i] = 1+base;
            }
        }
        return output;
    }


    @Override//Output muss woanderst hin vermutlich
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (boolean[] row:profile) {
            for (boolean b: row) {
                if (b){
                    buffer.append("F");
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
