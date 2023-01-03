package GPXrechner.WayModel.Entities;

import GPXrechner.Calculations.DistanceCalculator;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Units.Elevation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElevationProfile {
    private static final int yGranularity = 10;
    private boolean[][] profile;
    Elevation min,max;

    public ElevationProfile(Path path, int granularity) {
        this.profile = calculateElevationProfile(path,granularity);
    }

    public ElevationProfile(Path path){
        new ElevationProfile(path,10);
    }

    private boolean[][] calculateElevationProfile(Path path, int xGranularity) {
        ArrayList<Location> locations = path.getOrderedLocations();
        boolean[][] output = new boolean[xGranularity][yGranularity];
        int[] sectionLength = ProfileCalculation.split(locations.size() , xGranularity);
        List<List<Location>> locationClusters = new ArrayList<>();
        int processed = 0;
        for (int i = 0; i < xGranularity; i++){
            List<Location> valueSection = locations.subList(processed,processed+sectionLength[i]);
            locationClusters.add(valueSection);
            processed += sectionLength[i];
        }
        List<Elevation> heights = getHeights(locationClusters);
        this.max = Collections.max(heights);
        this.min = Collections.min(heights);
        List<Double> values = ProfileCalculation.normalize(heights.stream().map(e->e.getValue()).toList(),min.getValue(),max.getValue());
        for (int i = 0; i < xGranularity;i++) {
            for (int j = 0; j < yGranularity; j++) {
                if (values.get(i)*yGranularity >= j){
                    output[i][j] = true;
                }
            }
        }
        return output;
    }

    private List<Elevation> getHeights(List<List<Location>> locationClusters) {
        List<Elevation> heights = new ArrayList<>();
        Elevation firstAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(0));
        Elevation secondAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(1));
        if (firstAvg.getValue() < secondAvg.getValue()){
            heights.add(DistanceCalculator.calcMinAlt(locationClusters.get(0)));
        }
        else {
            heights.add(DistanceCalculator.calcMaxAlt(locationClusters.get(0)));
        }
        for (int i = 1; i < locationClusters.size() - 1;i++){
            Elevation prevAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(i-1));
            Elevation actAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(i));
            Elevation postAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(i+1));
            if (actAvg.getValue() > prevAvg.getValue() && actAvg.getValue() > postAvg.getValue()){
                heights.add(DistanceCalculator.calcMaxAlt(locationClusters.get(i)));
            }
            if (actAvg.getValue() < prevAvg.getValue() && actAvg.getValue() < postAvg.getValue()) {
                heights.add(DistanceCalculator.calcMinAlt(locationClusters.get(i)));
            }
            heights.add(DistanceCalculator.calcAvgAlt(locationClusters.get(i)));

        }
        Elevation lastAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(0));
        Elevation secondToLastAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(1));
        if (lastAvg.getValue() < secondToLastAvg.getValue()){
            heights.add(DistanceCalculator.calcMinAlt(locationClusters.get(locationClusters.size()-1)));
        }
        else {
            heights.add(DistanceCalculator.calcMaxAlt(locationClusters.get(locationClusters.size()-1)));
        }
        return heights;
    }

    public boolean[][] getProfile() {
        return profile;
    }
}
