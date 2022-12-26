package GPXrechner.WayModel;

import GPXrechner.WayModel.Units.Elevation;
import GPXrechner.WayModel.Units.Latitude;
import GPXrechner.WayModel.Units.Longitude;

public interface Location {
    public Latitude getLat();
    public Longitude getLon();
    public Elevation getEle();
}
