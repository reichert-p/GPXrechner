package GPXrechner.WayModel.Entities;

import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Units.Elevation;

import java.util.ArrayList;

public interface Path {
    ArrayList<Location> getOrderedLocations();
}
