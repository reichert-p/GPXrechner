package WayModel;

import WayModel.Units.Elevation;
import WayModel.Units.Latitude;
import WayModel.Units.Longitude;

public interface Location {
    public Latitude getLat();
    public Longitude getLon();
    public Elevation getEle();
}
