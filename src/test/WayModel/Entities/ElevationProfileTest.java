package test.WayModel.Entities;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.WayModel.Profiles.ElevationProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.GetTracks;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElevationProfileTest {

    @Test
    void getProfile() throws InsufficientDataException {
        Assertions.assertThrows(InsufficientDataException.class ,()-> new ElevationProfile(GetTracks.getMountainTrack()));
        ElevationProfile elevationProfile = new ElevationProfile(GetTracks.getMountainTrack(),7);
        boolean[][] matrix = elevationProfile.getProfile();
        assertEquals(true , matrix[0][0]);
        assertEquals(false, matrix[0][1]);
        assertEquals(true , matrix[1][1]);
        assertEquals(false, matrix[1][2]);
        assertEquals(true , matrix[2][4]);
        assertEquals(false, matrix[2][5]);
        assertEquals(true , matrix[3][8]);
        assertEquals(false, matrix[3][9]);
        assertEquals(true , matrix[4][8]);
        assertEquals(true , matrix[4][9]);
        assertEquals(true , matrix[5][6]);
        assertEquals(false, matrix[5][7]);
        assertEquals(true , matrix[6][0]);
        assertEquals(false, matrix[6][1]);
    }
}