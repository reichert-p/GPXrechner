package GPXrechner.Domain.WayModel;

import GPXrechner.Domain.WayModel.Units.Elevation;
import GPXrechner.Domain.WayModel.Units.Latitude;
import GPXrechner.Domain.WayModel.Units.Longitude;

import java.time.LocalDateTime;

public class TourPoint implements Location {
    private final TrackPoint tp;
    private final LocalDateTime time;

    public TourPoint(TrackPoint tp, LocalDateTime time) {
        this.tp = tp;
        this.time = time;
    }

    public TourPoint(double lat, double lon, double ele, LocalDateTime time) {
        this.tp = new TrackPoint(lat, lon, ele);
        this.time = time;
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

    public LocalDateTime getTime() {
        return time;
    }
}
