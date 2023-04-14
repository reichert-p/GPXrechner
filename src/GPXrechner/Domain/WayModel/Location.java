package GPXrechner.Domain.WayModel;

import GPXrechner.Domain.WayModel.Units.Elevation;
import GPXrechner.Domain.WayModel.Units.Latitude;
import GPXrechner.Domain.WayModel.Units.Longitude;

public interface Location {
    Latitude getLat();

    Longitude getLon();

    Elevation getEle();
}
