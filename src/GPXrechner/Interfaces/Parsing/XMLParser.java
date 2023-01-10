package GPXrechner.Interfaces.Parsing;

import GPXrechner.Calculations.TourSplitting.NoWayPointsExeption;
import GPXrechner.Calculations.TourSplitting.WayPointSet;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Entities.Track;

public interface XMLParser {
    public Tour parseTour(String pathName) throws NoTourException;
    public Track parseTrack(String pathName) throws NoTrackException;
    public WayPointSet parseWayPoints(String pathName) throws NoWayPointsExeption;
}
