package test.WayModel;


import GPXrechner.WayModel.AltitudeGain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AltitudeGainTest { //TODO change expected and actual
    AltitudeGain alt1,alt2,alt3;
    @BeforeEach
    void beforeEachTest(){
        alt1 = new AltitudeGain(200, 400);
        alt2 = new AltitudeGain(300);
        alt3 = new AltitudeGain(-400);
    }

    @Test
    void getUp() {
        Assertions.assertEquals(alt1.getUp(),200);
        Assertions.assertEquals(alt2.getUp(),300);
        Assertions.assertEquals(alt3.getUp(),0);
    }

    @Test
    void getDown() {
        Assertions.assertEquals(alt1.getDown(),400);
        Assertions.assertEquals(alt2.getDown(),0);
        Assertions.assertEquals(alt3.getDown(),400);
    }

    @Test
    void testToString() {
        Assertions.assertEquals(alt1.toString(), "up: 200.0m down: 400.0m");
    }

    @Test
    void addAltitude() {
        alt1.addAltitude(alt2);
        alt1.addAltitude(alt3);
        Assertions.assertEquals(alt1.getUp(),500);
        Assertions.assertEquals(alt1.getDown(), 800);
    }

    @Test
    void getManhattenNorm() {
        Assertions.assertEquals(alt1.getManhattenNorm(),600);
        Assertions.assertEquals(alt2.getManhattenNorm(),300);
        Assertions.assertEquals(alt3.getManhattenNorm(),400);

    }
}