package Entities;

import WayModel.Location;
import WayModel.TourPoint;

import java.util.ArrayList;
import java.util.Arrays;

public class Tour implements Path {
    private String description;
    private ArrayList<Location> tourPoints = new ArrayList<>();

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

    @Override
    public ArrayList<Location> getOrderedLocations() {
        return tourPoints;
    }
}