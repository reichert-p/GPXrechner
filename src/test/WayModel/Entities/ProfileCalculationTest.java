package test.WayModel.Entities;

import GPXrechner.Domain.WayModel.WayModel.Profiles.ProfileCalculation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileCalculationTest {
    @Test
    void normalize(){
        List<Double> list = new ArrayList<>();
        list.add(-10.0);
        list.add(0.0);
        list.add(10.0);
        list = ProfileCalculation.normalize(list);
        assertEquals(0,list.get(0));
        assertEquals(0.5,list.get(1));
        assertEquals(1,list.get(2));
    }

    @Test
    void normalizeFlatDiff(){
        List<Double> list = new ArrayList<>();
        list.add(4.0);
        list.add(4.0);
        list.add(4.0);
        list = ProfileCalculation.normalize(list);
        assertEquals(1,list.get(0));
        assertEquals(1,list.get(1));
        assertEquals(1,list.get(2));
    }

    @Test
    void split(){
        int[] sections = ProfileCalculation.split(22,5);
        assertEquals(5,sections[0]);
        assertEquals(5,sections[1]);
        assertEquals(4,sections[2]);
        assertEquals(4,sections[3]);
        assertEquals(4,sections[4]);
    }

    @Test
    void splitTooSmallGranularity(){
        int[] sections = ProfileCalculation.split(22,25);
        assertEquals(1,sections[19]);
        assertEquals(1,sections[20]);
        assertEquals(1,sections[21]);
        assertEquals(0,sections[22]);
    }
}