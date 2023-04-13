package test.Calculations;

import GPXrechner.Domain.Calculations.InsufficientDataException;
import GPXrechner.Domain.Calculations.SpeedHeuristics;
import GPXrechner.Plugin.Parsing.GPXReader.GPXToTour;
import GPXrechner.Plugin.Parsing.GPXReader.NoDataException;
import GPXrechner.Domain.WayModel.WayModel.Entities.Tour;
import GPXrechner.Domain.WayModel.WayModel.Location;
import GPXrechner.Domain.WayModel.WayModel.TourPoint;
import GPXrechner.Domain.WayModel.WayModel.Units.Pace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class SpeedHeuristicsTest {
    Tour[] tour = new Tour[3];
    List<TourPoint> tourPointList;

    void setUp(int i) throws NoDataException {
        GPXToTour tourParser = new GPXToTour();
        tourParser.read("Files\\GPX\\Tour\\radtour.gpx");
        tour[0] = tourParser.getTour();
        tourParser.read("Files\\GPX\\Tour\\Gartenstrasse.gpx");
        tour[1] = tourParser.getTour();
        tourParser.read("Files\\GPX\\Tour\\artificial.gpx");
        tour[2] = tourParser.getTour();

        List<Location> locations = tour[i].getOrderedLocations().subList(0, 4);
        tourPointList = locations.stream()
                .filter(e -> e instanceof TourPoint)
                .map(e -> (TourPoint) e)
                .toList();
    }

    @Test
    void calculateTime() throws NoDataException {
        setUp(0);
        Duration duration = SpeedHeuristics.calculateTime(tour[2]);
        Assertions.assertEquals(3 * 60, duration.getSeconds());
    }

    @Test
    void getDescendingHeuristic() throws NoDataException, InsufficientDataException {
        setUp(2);
        Pace descending = SpeedHeuristics.getDescendingHeuristic(tourPointList, new Pace(1000000));
        Assertions.assertEquals(12, (Math.round(descending.getValue() / 100)));
        Assertions.assertThrows(InsufficientDataException.class, () -> SpeedHeuristics.getClimbingHeuristic(new ArrayList<TourPoint>(), new Pace(17000)));
    }

    @Test
    void getClimbingHeuristic() throws InsufficientDataException, NoDataException {
        setUp(2);
        Pace climbing = SpeedHeuristics.getClimbingHeuristic(tourPointList, new Pace(1000000));
        Assertions.assertEquals(6, Math.round(climbing.getValue() / 100));
        Assertions.assertThrows(InsufficientDataException.class, () -> SpeedHeuristics.getClimbingHeuristic(new ArrayList<TourPoint>(), new Pace(17000)));
    }

    @Test
    void getHorizontalHeuristic() throws InsufficientDataException, NoDataException {
        setUp(0);
        Pace horizontal = SpeedHeuristics.getHorizontalHeuristic(tourPointList);
        Assertions.assertEquals(15, Math.round(horizontal.getValue() / 1000));
        Assertions.assertThrows(InsufficientDataException.class, () -> SpeedHeuristics.getHorizontalHeuristic(new ArrayList<TourPoint>()));
    }


}