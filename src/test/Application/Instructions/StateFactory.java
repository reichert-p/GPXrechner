package test.Application.Instructions;

import GPXrechner.Application.States.Initial;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Interfaces.Parsing.DOMParser;
import GPXrechner.Interfaces.Parsing.NoTourException;
import GPXrechner.Interfaces.Parsing.NoTrackException;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Entities.Track;

public class StateFactory {
    public static Initial getInitialState(){
        return new Initial();
    }

    public static TourLoaded getTourLoaded() throws NoTourException {
        DOMParser domParser = new DOMParser();
        Tour tour = null;
        tour = domParser.parseTour("Files/GPX/TOUR/artificial.gpx");
        return new TourLoaded(tour);
    }

    public static TrackLoaded getTrackLoaded(){
        DOMParser domParser = new DOMParser();
        Track track = null;
        try{
            track = domParser.parseTrack("Files/GPX/TOUR/artificial.gpx");
        }catch (Exception | NoTrackException e){

        }
        return new TrackLoaded(track);
    }
}
