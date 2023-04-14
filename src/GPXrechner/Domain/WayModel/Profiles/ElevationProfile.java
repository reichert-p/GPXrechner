package GPXrechner.Domain.WayModel.Profiles;

import GPXrechner.Domain.Calculations.DistanceCalculator;
import GPXrechner.Domain.Calculations.InsufficientDataException;
import GPXrechner.Domain.WayModel.Entities.Path;
import GPXrechner.Domain.WayModel.Location;
import GPXrechner.Domain.WayModel.Units.Elevation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ElevationProfile {
    private static final int yGranularity = 10;
    private boolean[][] profile;

    public ElevationProfile(Path path, int granularity) throws InsufficientDataException {
        this.profile = calculateElevationProfile(path, granularity);
    }

    public ElevationProfile(Path path) throws InsufficientDataException {
        new ElevationProfile(path, 10);
    }

    private boolean[][] calculateElevationProfile(Path path, int xGranularity) throws InsufficientDataException {
        ArrayList<Location> locations = path.getOrderedLocations();
        boolean[][] output = new boolean[xGranularity][yGranularity];
        int[] sectionLength = ProfileCalculation.split(locations.size(), xGranularity);
        List<List<Location>> locationClusters = new ArrayList<>();
        int processed = 0;
        for (int i = 0; i < xGranularity; i++) {
            List<Location> valueSection = locations.subList(processed, processed + sectionLength[i]);
            locationClusters.add(valueSection);
            processed += sectionLength[i];
        }
        List<Elevation> heights = getHeights(locationClusters);
        List<Double> values = ProfileCalculation.normalize(heights.stream().map(e -> e.getValue()).collect(Collectors.toCollection(ArrayList::new)));
        for (int i = 0; i < xGranularity; i++) {
            for (int j = 0; j < yGranularity; j++) {
                if (values.get(i) * yGranularity >= j) {
                    output[i][j] = true;
                }
            }
        }
        return output;
    }

    private List<Elevation> getHeights(List<List<Location>> locationClusters) throws InsufficientDataException {
        List<Elevation> heights = new ArrayList<>();
        Elevation firstAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(0));
        Elevation secondAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(1));
        if (firstAvg.getValue() < secondAvg.getValue()) {
            heights.add(DistanceCalculator.calcMinAlt(locationClusters.get(0)));
        } else {
            heights.add(DistanceCalculator.calcMaxAlt(locationClusters.get(0)));
        }
        for (int i = 1; i < locationClusters.size() - 1; i++) {
            Elevation prevAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(i - 1));
            Elevation actAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(i));
            Elevation postAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(i + 1));
            if (actAvg.getValue() > prevAvg.getValue() && actAvg.getValue() > postAvg.getValue()) {
                heights.add(DistanceCalculator.calcMaxAlt(locationClusters.get(i)));
            } else if (actAvg.getValue() < prevAvg.getValue() && actAvg.getValue() < postAvg.getValue()) {
                heights.add(DistanceCalculator.calcMinAlt(locationClusters.get(i)));
            } else {
                heights.add(DistanceCalculator.calcAvgAlt(locationClusters.get(i)));
            }
        }
        Elevation lastAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(locationClusters.size() - 1));
        Elevation secondToLastAvg = DistanceCalculator.calcAvgAlt(locationClusters.get(locationClusters.size() - 2));
        if (lastAvg.getValue() < secondToLastAvg.getValue()) {
            heights.add(DistanceCalculator.calcMinAlt(locationClusters.get(locationClusters.size() - 1)));
        } else {
            heights.add(DistanceCalculator.calcMaxAlt(locationClusters.get(locationClusters.size() - 1)));
        }
        return heights;
    }

    public boolean[][] getProfile() {
        return profile;
    }
}
