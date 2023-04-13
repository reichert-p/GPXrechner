package GPXrechner.Domain.WayModel.WayModel;

import GPXrechner.Domain.WayModel.WayModel.Units.Elevation;
import GPXrechner.Domain.WayModel.WayModel.Units.Latitude;
import GPXrechner.Domain.WayModel.WayModel.Units.Longitude;

public class WayPoint implements Location {
    private final TrackPoint tp;
    private final String description;

    public WayPoint(TrackPoint tp, String description) {
        this.tp = tp;
        this.description = description;
    }

    public WayPoint(double lat, double lon, double ele, String description) {
        this.tp = new TrackPoint(lat, lon, ele);
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public Latitude getLat() {
        return tp.getLat();
    }

    @Override
    public Longitude getLon() {
        return tp.getLon();
    }

    @Override
    public Elevation getEle() {
        return tp.getEle();
    }
}
