package GPXrechner.Domain.WayModel.Entities;

import GPXrechner.Domain.WayModel.Location;

import java.util.ArrayList;

public interface Path {
    ArrayList<Location> getOrderedLocations();
}
