package test.WayModel.Units;

import GPXrechner.WayModel.Units.Distance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceTest {

    Distance dist;
    @BeforeEach
    void setUp() {
        dist = new Distance(250);
    }

    @Test
    void testWrongCreation(){
        assertThrows(RuntimeException.class,
                () -> new Distance(-300));
    }

    @Test
    void testToString() {
        assertEquals("Distance: 250 meters", dist.toString());
    }

    @Test
    void addDistance() {
        dist.addDistance(new Distance(500.5));
        assertEquals(dist.getValue(),750.5);
    }


}