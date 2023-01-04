package test.WayModel.Entities;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Inputhandling.Parsing.DOMParser;
import GPXrechner.Inputhandling.Parsing.NoTourException;
import GPXrechner.Inputhandling.Parsing.XMLParser;
import GPXrechner.WayModel.Entities.ElevationProfile;
import GPXrechner.WayModel.Entities.SpeedProfile;
import GPXrechner.WayModel.Entities.Tour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.Calculations.GetTracks;

import static org.junit.jupiter.api.Assertions.*;

class SpeedProfileTest {
    @Test
    void getProfile() throws InsufficientDataException, NoTourException {
        XMLParser xmlParser = new DOMParser();
        Tour tour1 = xmlParser.parseTour("Files\\GPX\\Tour\\radtour.gpx");
        Tour tour2 = xmlParser.parseTour("Files\\GPX\\Tour\\artificial.gpx");
        Assertions.assertThrows(InsufficientDataException.class ,()-> new SpeedProfile(tour2));
        SpeedProfile speedProfile = new SpeedProfile(tour1,7);
        boolean[][] matrix = speedProfile.getProfile();
        assertEquals(true , matrix[0][7]);
        assertEquals(false, matrix[0][8]);
        assertEquals(true , matrix[1][4]);
        assertEquals(false, matrix[1][5]);
        assertEquals(true , matrix[2][6]);
        assertEquals(false, matrix[2][7]);
        assertEquals(true , matrix[3][6]);
        assertEquals(false, matrix[3][7]);
        assertEquals(true , matrix[4][8]);
        assertEquals(true , matrix[4][9]);
        assertEquals(true , matrix[5][5]);
        assertEquals(false, matrix[5][6]);
        assertEquals(true , matrix[6][0]);
        assertEquals(false, matrix[6][1]); //beim Aufzeichnen Stoppen vergessen:)
    }

}