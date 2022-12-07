package Entities;

import WayModel.TourPoint;

import java.util.ArrayList;
import java.util.Arrays;

public class Tour {
    private String description;
    private ArrayList<TourPoint> tourPoints = new ArrayList<>();

    public Tour(String description) {
        this.description = description;
    }

    public void addTourPoints(TourPoint[] t){
        tourPoints.addAll(Arrays.stream(t).toList());
    }

    @Override
    public String toString() {
        return description;
    }
}
