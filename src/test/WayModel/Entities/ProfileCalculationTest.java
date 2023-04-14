package test.WayModel.Entities;

import GPXrechner.Domain.WayModel.Profiles.ProfileCalculation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileCalculationTest {

    @ParameterizedTest
    @MethodSource("normalizeValues")
    void normalize(Double[] input, Double[] expected){
        List<Double> list = new ArrayList<>(Arrays.asList(input));
        list = ProfileCalculation.normalize(list);
        assertEquals(Arrays.asList(expected), list.stream().toList());
    }

    private static List<Arguments> normalizeValues() {
        return List.of(
                Arguments.of(new Double[]{4.0,4.0,4.0}, new Double[]{1.0,1.0,1.0}),
                Arguments.of(new Double[]{-10.0,0.0,10.0}, new Double[]{0.0,0.5,1.0}),
                Arguments.of(new Double[]{2.0,3.0,3.0,4.0}, new Double[]{0.0,0.5,0.5,1.0}),
                Arguments.of(new Double[]{0.0,3.0,6.0,2.0,8.0,9.0,10.0,2.0,3.0,4.0,5.0,6.0}, new Double[]{0.0,0.3,0.6,0.2,0.8,0.9,1.0,0.2,0.3,0.4,0.5,0.6}),
                Arguments.of(new Double[]{4.0}, new Double[]{1.0}),
                Arguments.of(new Double[]{}, new Double[]{}),
                Arguments.of(new Double[]{9000.0,-500.0, 0.0}, new Double[]{1.0,0.0,500.0/9500}),
                Arguments.of(new Double[]{60.75, 105.75, 240.75}, new Double[]{0.0,0.25,1.0})
                );
    }

    private static List<Arguments> splitValues() {
        return List.of(
                Arguments.of(25, 0, new int[]{}),
                Arguments.of(25, 1, new int[]{25}),
                Arguments.of(25, 2, new int[]{13,12}),
                Arguments.of(25, 3, new int[]{9,8,8}),
                Arguments.of(25, 4, new int[]{7,6,6,6}),
                Arguments.of(0, 5, new int[]{0,0,0,0,0}),
                Arguments.of(3, 5, new int[]{1,1,1,0,0}),
                Arguments.of(Integer.MAX_VALUE, 4, new int[]{Integer.MAX_VALUE/4 +1,Integer.MAX_VALUE/4 +1,Integer.MAX_VALUE/4 +1,Integer.MAX_VALUE/4}),
                Arguments.of(-3, 5, new int[]{}),
                Arguments.of(42, -5, new int[]{})
                );
    }


    @ParameterizedTest
    @MethodSource("splitValues")
    void split(int pool, int sections, int[] expected ){
        int[] result = ProfileCalculation.split(pool,sections);
        assertArrayEquals(expected, result);
    }
}