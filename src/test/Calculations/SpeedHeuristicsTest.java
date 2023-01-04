package test.Calculations;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.SpeedHeuristics;
import GPXrechner.Inputhandling.Parsing.DOMParser;
import GPXrechner.Inputhandling.Parsing.NoTourException;
import GPXrechner.Inputhandling.Parsing.XMLParser;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.TourPoint;
import GPXrechner.WayModel.Units.Pace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpeedHeuristicsTest {
    Tour[] tour = new Tour[3];
    List<TourPoint> tourPointList;

    void setUp(int i) throws NoTourException {
       XMLParser xmlParser = new DOMParser();
       tour[0] = xmlParser.parseTour("Files\\GPX\\Tour\\radtour.gpx");
       tour[1] = xmlParser.parseTour("Files\\GPX\\Tour\\Gartenstrasse.gpx");
       tour[2] = xmlParser.parseTour("Files\\GPX\\Tour\\artificial.gpx");
       List<Location> locations = tour[i].getOrderedLocations().subList(0,4);
       tourPointList = locations.stream()
               .filter(e -> e instanceof TourPoint)
               .map(e -> (TourPoint)e)
               .toList();
    }

    @Test
    void calculateTime() throws NoTourException {
        setUp(0);
        Duration duration = SpeedHeuristics.calculateTime(tour[2]);
        Assertions.assertEquals(3*60,duration.getSeconds());
    }

    @Test
    void getDescendingHeuristic() throws NoTourException, InsufficientDataException {
        setUp(2);
        Pace descending = SpeedHeuristics.getDescendingHeuristic(tourPointList,new Pace(1000000));
        Assertions.assertEquals(12,(Math.round(descending.getValue()/100)));
        Assertions.assertThrows(InsufficientDataException.class,()-> SpeedHeuristics.getClimbingHeuristic(new ArrayList<TourPoint>(),new Pace(17000)));
    }

    @Test
    void getClimbingHeuristic() throws InsufficientDataException, NoTourException {
        setUp(2);
        Pace climbing = SpeedHeuristics.getClimbingHeuristic(tourPointList,new Pace(1000000));
        Assertions.assertEquals(6,Math.round(climbing.getValue()/100));
        Assertions.assertThrows(InsufficientDataException.class,()-> SpeedHeuristics.getClimbingHeuristic(new ArrayList<TourPoint>(),new Pace(17000)));
    }

    @Test
    void getHorizontalHeuristic() throws InsufficientDataException, NoTourException {
        setUp(0);
        Pace horizontal = SpeedHeuristics.getHorizontalHeuristic(tourPointList);
        Assertions.assertEquals(15,Math.round(horizontal.getValue()/1000));
        Assertions.assertThrows(InsufficientDataException.class,()-> SpeedHeuristics.getHorizontalHeuristic(new ArrayList<TourPoint>()));
    }


}