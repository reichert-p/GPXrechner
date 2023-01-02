package GPXrechner.Inputhandling.Parsing;

import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Entities.Track;

public interface XMLParser {
    public Tour parseTour(String pathname) throws NoTourException;
    public Track parseTrack(String pathname) throws NoTrackException;
}
