package GPXrechner.WayModel;

import GPXrechner.WayModel.Units.Elevation;
import GPXrechner.WayModel.Units.Latitude;
import GPXrechner.WayModel.Units.Longitude;

import java.time.LocalDateTime;

public class TourPoint implements Location{
    private TrackPoint tp;
    private LocalDateTime time;

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
