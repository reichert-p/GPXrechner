package test.Application.Instructions;

import GPXrechner.Application.Instructions.GenerateTrack;
import GPXrechner.Application.Instructions.GetAltitudeDifference;
import GPXrechner.Application.Instructions.GetDistance;
import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Parsing.GPXReader.NoDataException;
import GPXrechner.Interfaces.Parsing.NoTourException;
import GPXrechner.Interfaces.Parsing.NoTrackException;
import GPXrechner.WayModel.Entities.Track;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class InstructionsTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void GenerateTrackTest() throws InvalidStateException, NoDataException {
        GenerateTrack gt = new GenerateTrack();
        Assertions.assertThrows(InvalidStateException.class,()->gt.execute(StateFactory.getTrackLoaded()));

        State getTourLoaded = StateFactory.getTourLoaded();
        State getTrackLoaded = gt.execute(getTourLoaded);
        assertTrue(getTrackLoaded.getPath() instanceof Track);
        assertEquals(getTourLoaded.getPath().toString() , getTrackLoaded.getPath().toString());
        assertEquals(getTourLoaded.getPath().getOrderedLocations().get(0).getEle() , getTrackLoaded.getPath().getOrderedLocations().get(0).getEle());
        assertEquals(getTourLoaded.getPath().getOrderedLocations().get(0).getLat() , getTrackLoaded.getPath().getOrderedLocations().get(0).getLat());
        assertEquals(getTourLoaded.getPath().getOrderedLocations().get(0).getLon() , getTrackLoaded.getPath().getOrderedLocations().get(0).getLon());
    }

    @Test
    void GetAltitudeDifferenceTest() throws InvalidStateException, NoDataException{
        GetAltitudeDifference gad = new GetAltitudeDifference();
        Assertions.assertThrows(InvalidStateException.class,()->gad.execute(StateFactory.getInitialState()));

        State getTrackLoaded = StateFactory.getTrackLoaded();
        gad.execute(getTrackLoaded);
        assertEquals("Elevation difference of Path Im Haus: up: 19.0m down: 19.0m\r\n", outContent.toString());
    }
    @Test
    void GetDistanceTest() throws InvalidStateException, NoDataException{
        GetDistance gd = new GetDistance();
        Assertions.assertThrows(InvalidStateException.class,()->gd.execute(StateFactory.getInitialState()));

        State getTrackLoaded = StateFactory.getTrackLoaded();
        gd.execute(getTrackLoaded);
        assertEquals("Length of Path Im Haus: 3339 meters", outContent.toString());
    }

}
