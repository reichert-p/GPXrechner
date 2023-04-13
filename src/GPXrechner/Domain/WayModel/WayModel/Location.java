package GPXrechner.Domain.WayModel.WayModel;

import GPXrechner.Domain.WayModel.WayModel.Units.Elevation;
import GPXrechner.Domain.WayModel.WayModel.Units.Latitude;
import GPXrechner.Domain.WayModel.WayModel.Units.Longitude;

public interface Location {
    Latitude getLat();

    Longitude getLon();

    Elevation getEle();
}
