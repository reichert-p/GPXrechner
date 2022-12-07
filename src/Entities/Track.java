package Entities;

import WayModel.TrackPoint;

import java.util.ArrayList;

public class Track {
    private String description;
    private ArrayList<TrackPoint> trackPoints = new ArrayList<>();

    @Override
    public String toString() {
        return description;
    }
}
