package test.Calculations.Toursplitting;

import GPXrechner.Calculations.TourSplitting.Representation;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class RepresentationTest {

    @Test
    void getDefaultBitString() {
        Representation defaultRepresentation = new Representation(5);
        assertEquals(true,defaultRepresentation.getBitString()[0]);
        assertEquals(true,defaultRepresentation.getBitString()[1]);
        assertEquals(true,defaultRepresentation.getBitString()[2]);
        assertEquals(true,defaultRepresentation.getBitString()[3]);
        assertEquals(true,defaultRepresentation.getBitString()[4]);
    }

    @Test
    void bitFlip() {
        Representation defaultRepresentation = new Representation(5);
        var array = defaultRepresentation.bitFlip().getBitString();
        assertEquals(4,countTrues(array));
        array = defaultRepresentation.bitFlip().bitFlip().getBitString();
        assertTrue(countTrues(array) == 3 || countTrues(array) == 5);
    }

    private int countTrues(boolean[] array){
        int i = 0;
        for (boolean b: array) {
            if (b){
                i++;
            }
        }
        return i;
    }

    @Test
    void crossover() {
        Representation lparent = new Representation(new boolean[]{false, true, false, false, false});
        Representation rparent = new Representation(new boolean[]{false, true, true, false, false,  false});
        var newGenes = lparent.crossover(rparent).getBitString();
        assertEquals(false,newGenes[0]);
        assertEquals(true,newGenes[1]);
        assertEquals(false,newGenes[3]);
        assertEquals(false,newGenes[4]);
    }
}