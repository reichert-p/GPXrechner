package GPXrechner.WayModel.Entities;

import GPXrechner.WayModel.Location;

import java.util.ArrayList;

public interface Path {
    ArrayList<Location> getOrderedLocations();
}
