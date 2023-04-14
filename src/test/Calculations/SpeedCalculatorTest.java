package test.Calculations;

import GPXrechner.Domain.Calculations.InsufficientDataException;
import GPXrechner.Domain.Calculations.MovementSpeed.PersonalSpeed;
import GPXrechner.Domain.Calculations.SpeedCalculator;
import GPXrechner.Plugin.Parsing.GPXReader.GPXToTour;
import GPXrechner.Plugin.Parsing.GPXReader.NoDataException;
import GPXrechner.Domain.WayModel.Entities.Tour;
import GPXrechner.Domain.WayModel.Location;
import GPXrechner.Domain.WayModel.TourPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class SpeedCalculatorTest {
    Tour[] tour = new Tour[2];
    @BeforeEach
    void setUp() throws NoDataException {
        GPXToTour tourParser = new GPXToTour();
        tourParser.read("Files\\GPX\\Tour\\radtour.gpx");
        tour[0] = tourParser.getTour();
        tourParser.read("Files\\GPX\\Tour\\Gartenstrasse.gpx");
        tour[1] = tourParser.getTour();
    }

    @Test
    void predictPMSSingle() throws InsufficientDataException {
        PersonalSpeed personalSpeed = SpeedCalculator.predictPersonalMovementSpeed(tour[0]);
        Assertions.assertEquals(24425,Math.round(personalSpeed.getHorizontalSpeed().getValue()));
        Assertions.assertEquals(1111,Math.round(personalSpeed.getDescendingSpeed().getValue()));
        Assertions.assertEquals(1173,Math.round(personalSpeed.getClimbingSpeed().getValue()));
        Assertions.assertThrows(InsufficientDataException.class,()->SpeedCalculator.predictPersonalMovementSpeed(tour[1]));
        }

    @Test
    void testPredictPMSMultiple() throws InsufficientDataException {
        PersonalSpeed personalSpeed = SpeedCalculator.predictPersonalMovementSpeed(tour);
        Assertions.assertEquals(24425,Math.round(personalSpeed.getHorizontalSpeed().getValue()));
        Assertions.assertEquals(1111,Math.round(personalSpeed.getDescendingSpeed().getValue()));
        Assertions.assertEquals(1173,Math.round(personalSpeed.getClimbingSpeed().getValue()));
    }

    @Test
    void calculateSpeedDeviation() throws InsufficientDataException {
        List<Location> locations = tour[0].getOrderedLocations().subList(120,200);
        Tour section = new Tour("Section");
        section.addTourPoints(locations.toArray(TourPoint[]::new));

        double result = SpeedCalculator.calculateSpeedDeviation(tour[0],section);
        Assertions.assertEquals(11,Math.round(result * 10));
    }

    @Test
    void calculateSpeedDeviationFail() throws InsufficientDataException {
        List<Location> locations = tour[0].getOrderedLocations().subList(1,10);
        Tour section = new Tour("Section");
        section.addTourPoints(locations.toArray(TourPoint[]::new));
        Assertions.assertThrows(InsufficientDataException.class,()->SpeedCalculator.calculateSpeedDeviation(tour[1],section));
    }
}