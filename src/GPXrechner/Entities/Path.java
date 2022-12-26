package GPXrechner.Entities;

import GPXrechner.WayModel.Location;

import java.util.ArrayList;

public interface Path {
    public ArrayList<Location> getOrderedLocations();
}
