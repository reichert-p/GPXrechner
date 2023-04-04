package test.WayModel.Entities;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Interfaces.Parsing.GPXReader.GPXToTour;
import GPXrechner.Interfaces.Parsing.GPXReader.NoDataException;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Profiles.SpeedProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpeedProfileTest {
    @Test
    void getProfile() throws InsufficientDataException, NoDataException {
        GPXToTour tourParser = new GPXToTour();
        tourParser.read("Files\\GPX\\Tour\\radtour.gpx");
        Tour tour1 = tourParser.getTour();
        tourParser.read("Files\\GPX\\Tour\\artificial.gpx");
        Tour tour2 = tourParser.getTour();
        Assertions.assertThrows(InsufficientDataException.class ,()-> new SpeedProfile(tour2));
        SpeedProfile speedProfile = new SpeedProfile(tour1,7);
        boolean[][] matrix = speedProfile.getProfile();
        assertEquals(true , matrix[0][7]);
        assertEquals(false, matrix[0][8]);
        assertEquals(true , matrix[1][3]);
        assertEquals(false, matrix[1][4]);
        assertEquals(true , matrix[2][4]);
        assertEquals(false, matrix[2][5]);
        assertEquals(true , matrix[3][5]);
        assertEquals(false, matrix[3][6]);
        assertEquals(true , matrix[4][7]);
        assertEquals(true , matrix[4][8]);
        assertEquals(true , matrix[5][4]);
        assertEquals(false, matrix[5][5]);
        assertEquals(true , matrix[6][0]);
        assertEquals(false, matrix[6][1]);
    }
}