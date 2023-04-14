package test.WayModel;


import GPXrechner.Domain.WayModel.ElevationGain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElevationGainTest {
    ElevationGain alt1,alt2,alt3;
    @BeforeEach
    void beforeEachTest(){
        alt1 = new ElevationGain(200, 400);
        alt2 = new ElevationGain(300);
        alt3 = new ElevationGain(-400);
    }

    @Test
    void getUp() {
        Assertions.assertEquals(200, alt1.getUp());
        Assertions.assertEquals(300, alt2.getUp());
        Assertions.assertEquals(0, alt3.getUp());
    }

    @Test
    void getDown() {
        Assertions.assertEquals(400, alt1.getDown());
        Assertions.assertEquals(0, alt2.getDown());
        Assertions.assertEquals(400, alt3.getDown());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("up: 200.0m down: 400.0m", alt1.toString());
    }

    @Test
    void addElevation() {
        alt1 = alt1.addElevationGain(alt2);
        alt1 = alt1.addElevationGain(alt3);
        Assertions.assertEquals(500,alt1.getUp());
        Assertions.assertEquals(800,alt1.getDown());
    }

    @Test
    void getManhattanNorm() {
        Assertions.assertEquals(600, alt1.getManhattenNorm());
        Assertions.assertEquals(300, alt2.getManhattenNorm());
        Assertions.assertEquals(400, alt3.getManhattenNorm());
    }
}