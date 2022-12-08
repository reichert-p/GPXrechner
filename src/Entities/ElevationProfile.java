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
        int sectionlength = locations.size() / xGranularity;
        List<Double> heigts = new ArrayList<>();
        for (int i = sectionlength; i < locations.size(); i+= sectionlength){
            heigts.add(DistanceCalculator.calcAvgAlt(locations.subList(i-sectionlength,i)));
        }
        this.max = Collections.max(heigts);
        this.min = Collections.min(heigts);
        heigts = normalize(heigts,min,max);
        for (int i = 0; i < xGranularity;i++) {
            for (int j = 0; j < yGranularity; j++) {
                if (heigts.get(i)*yGranularity >= j){
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
