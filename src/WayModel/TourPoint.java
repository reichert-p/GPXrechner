package WayModel;

import java.util.Date;

public class TourPoint implements Location{
    private TrackPoint tp;
    private Date time;

    public TourPoint(TrackPoint tp, Date time) {
        this.tp = tp;
        this.time = time;
    }

    public TourPoint(double lat, double lon, double ele, Date time) {
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

    public Date getTime() {
        return time;
    }
}
