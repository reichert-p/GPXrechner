package test.WayModel.Units;

import GPXrechner.Domain.WayModel.Units.Distance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals("250 meters", dist.toString());
    }

    @Test
    void addDistance() {
        dist = dist.addDistance(new Distance(500.5));
        assertEquals(dist.getValue(),750.5);
    }
}