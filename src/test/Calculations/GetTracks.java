package test.Calculations;

import GPXrechner.WayModel.Entities.Track;
import GPXrechner.WayModel.TrackPoint;

public class GetTracks {
    public static Track getMountainTrack(){ //Hochvogel traverse
        Track t =  new Track("Hochvogel traverse");
        TrackPoint[] pointList = {
                new TrackPoint(47.402998, 10.407784, 1242.83478),
                new TrackPoint(47.400906, 10.410466, 1271.47648),
                new TrackPoint(47.392451, 10.420401, 1840.3752),
                new TrackPoint(47.383879, 10.430915, 2361.26704),
                new TrackPoint(47.380317, 10.436816, 2588.5713),
                new TrackPoint(47.385213, 10.452795, 2049.21241),
                new TrackPoint(47.366005, 10.482999, 1095.66412),
        };
        t.addTrackPoints(pointList);
        return t;
    }

    public static Track getStraightTrack(){ //Hochvogel traverse
        Track t =  new Track("flaLine");
        TrackPoint[] pointList = {
                new TrackPoint(47.4, 10, 0),
                new TrackPoint(47.5, 10, 0),
                new TrackPoint(47.6, 10, 0),
                new TrackPoint(47.7, 10, 0),
        };
        t.addTrackPoints(pointList);
        return t;
    }
}
