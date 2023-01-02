package GPXrechner.Inputhandling.Parsing;

import GPXrechner.Entities.Tour;
import GPXrechner.Entities.Track;

public interface XMLParser {
    public Tour parseTour(String pathname);
    public Track parseTrack(String pathname);
}
