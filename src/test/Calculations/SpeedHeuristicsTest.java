package test.Calculations;

import GPXrechner.Domain.Calculations.InsufficientDataException;
import GPXrechner.Domain.Calculations.SpeedHeuristics;
import GPXrechner.Plugin.Parsing.GPXReader.GPXToTour;
import GPXrechner.Plugin.Parsing.GPXReader.NoDataException;
import GPXrechner.Domain.WayModel.Entities.Tour;
import GPXrechner.Domain.WayModel.Location;
import GPXrechner.Domain.WayModel.TourPoint;
import GPXrechner.Domain.WayModel.Units.Pace;
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
        Assertions.assertEquals(180, duration.getSeconds());
    }

    @Test
    void getDescendingHeuristic() throws NoDataException, InsufficientDataException {
        setUp(2);
        Pace descending = SpeedHeuristics.getDescendingHeuristic(tourPointList, new Pace(1000000));
        Assertions.assertEquals(1200, descending.getValue(),  50);
        Assertions.assertThrows(InsufficientDataException.class, () -> SpeedHeuristics.getClimbingHeuristic(new ArrayList<TourPoint>(), new Pace(17000)));
    }

    @Test
    void getClimbingHeuristic() throws InsufficientDataException, NoDataException {
        setUp(2);
        Pace climbing = SpeedHeuristics.getClimbingHeuristic(tourPointList, new Pace(1000000));
        Assertions.assertEquals(600, climbing.getValue(), 50);
        Assertions.assertThrows(InsufficientDataException.class, () -> SpeedHeuristics.getClimbingHeuristic(new ArrayList<TourPoint>(), new Pace(17000)));
    }

    @Test
    void getHorizontalHeuristic() throws InsufficientDataException, NoDataException {
        setUp(0);
        Pace horizontal = SpeedHeuristics.getHorizontalHeuristic(tourPointList);
        Assertions.assertEquals(15000, horizontal.getValue(), 500);
        Assertions.assertThrows(InsufficientDataException.class, () -> SpeedHeuristics.getHorizontalHeuristic(new ArrayList<TourPoint>()));
    }


}