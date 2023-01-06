package test.Calculations;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.MovementSpeed.PersonalSpeed;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Inputhandling.Parsing.DOMParser;
import GPXrechner.Inputhandling.Parsing.NoTourException;
import GPXrechner.Inputhandling.Parsing.XMLParser;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.TourPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpeedCalculatorTest {
    Tour[] tour = new Tour[2];
    @BeforeEach
    void setUp() throws NoTourException {
        XMLParser xmlParser = new DOMParser();
        tour[0] = xmlParser.parseTour("Files\\GPX\\Tour\\radtour.gpx");
        tour[1] = xmlParser.parseTour("Files\\GPX\\Tour\\Gartenstrasse.gpx");
    }

    @Test
    void predictPersonalMovementSpeedSingle() throws InsufficientDataException {
        PersonalSpeed personalSpeed = SpeedCalculator.predictPersonalMovementSpeed(tour[0]);
        Assertions.assertEquals(15890,Math.round(personalSpeed.getHorizontalSpeed().getValue()));
        Assertions.assertEquals(791,Math.round(personalSpeed.getDescendingSpeed().getValue()));
        Assertions.assertEquals(912,Math.round(personalSpeed.getClimbingSpeed().getValue()));
        Assertions.assertThrows(InsufficientDataException.class,()->SpeedCalculator.predictPersonalMovementSpeed(tour[1]));
        }

    @Test
    void testPredictPersonalMovementSpeedMultiple() throws InsufficientDataException {
        PersonalSpeed personalSpeed = SpeedCalculator.predictPersonalMovementSpeed(tour);
        Assertions.assertEquals(15890,Math.round(personalSpeed.getHorizontalSpeed().getValue()));
        Assertions.assertEquals(791,Math.round(personalSpeed.getDescendingSpeed().getValue()));
        Assertions.assertEquals(912,Math.round(personalSpeed.getClimbingSpeed().getValue()));
    }

    @Test
    void calculateSpeedDeviation() throws InsufficientDataException {
        List<Location> locations = tour[0].getOrderedLocations().subList(120,200);
        Tour section = new Tour("Section");
        section.addTourPoints(locations.toArray(TourPoint[]::new));

        double result = SpeedCalculator.calculateSpeedDeviation(tour[0],section);
        Assertions.assertEquals(18,Math.round(result * 10));
    }

    @Test
    void calculateSpeedDeviationFail() throws InsufficientDataException {
        List<Location> locations = tour[0].getOrderedLocations().subList(1,10);
        Tour section = new Tour("Section");
        section.addTourPoints(locations.toArray(TourPoint[]::new));
        Assertions.assertThrows(InsufficientDataException.class,()->SpeedCalculator.calculateSpeedDeviation(tour[1],section));
    }
}