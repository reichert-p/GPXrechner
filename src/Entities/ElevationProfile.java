package Entities;

import Calculations.DistanceCalculator;
import WayModel.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElevationProfile {
    private static final int yGranularity = 10;
    private boolean[][] profile;
    double min,max;

    public ElevationProfile(Path path, int granularity) {
        this.profile = calculateElevationProfile(path,granularity);
    }

    public ElevationProfile(Path path){
        new ElevationProfile(path,10);
    }
//TODO this needs refactoring
    private boolean[][] calculateElevationProfile(Path path, int xGranularity) {
        ArrayList<Location> locations = path.getOrderedLocations();
        boolean[][] output = new boolean[xGranularity][yGranularity];

        int[] sectionLength = split(locations.size() , xGranularity);
        List<Double> heights = new ArrayList<>();
        int processed = 0;
        for (int i = 0; i < xGranularity; i++){
            heights.add(DistanceCalculator.calcAvgAlt(locations.subList(processed,processed+sectionLength[i])));
            processed += sectionLength[i];
        }
        this.max = Collections.max(heights);
        this.min = Collections.min(heights);
        heights = normalize(heights,min,max);
        for (int i = 0; i < xGranularity;i++) {
            for (int j = 0; j < yGranularity; j++) {
                if (heights.get(i)*yGranularity >= j){
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
