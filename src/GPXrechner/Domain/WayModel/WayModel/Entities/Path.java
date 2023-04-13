package GPXrechner.Domain.WayModel.WayModel.Entities;

import GPXrechner.Domain.WayModel.WayModel.Location;

import java.util.ArrayList;

public interface Path {
    ArrayList<Location> getOrderedLocations();
}
