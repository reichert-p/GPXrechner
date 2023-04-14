package GPXrechner.Domain.WayModel;

import GPXrechner.Domain.WayModel.Units.Elevation;
import GPXrechner.Domain.WayModel.Units.Latitude;
import GPXrechner.Domain.WayModel.Units.Longitude;

public class TrackPoint implements Location {
    private final Latitude lat;
    private final Longitude lon;
    private final Elevation ele;

    public TrackPoint(Latitude lat, Longitude lon, Elevation ele) {
        this.lat = lat;
        this.lon = lon;
        this.ele = ele;
    }

    public TrackPoint(double lat, double lon, double ele) {
        this.lat = new Latitude(lat);
        this.lon = new Longitude(lon);
        this.ele = new Elevation(ele);
    }

    @Override
    public Latitude getLat() {
        return lat;
    }

    @Override
    public Longitude getLon() {
        return lon;
    }

    @Override
    public Elevation getEle() {
        return ele;
    }
}
