package test.Application.Instructions;

import GPXrechner.Application.States.Initial;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Plugin.Parsing.GPXReader.GPXToTour;
import GPXrechner.Plugin.Parsing.GPXReader.GPXToTrack;
import GPXrechner.Plugin.Parsing.GPXReader.NoDataException;
import GPXrechner.Domain.WayModel.WayModel.Entities.Tour;
import GPXrechner.Domain.WayModel.WayModel.Entities.Track;

public class StateFactory {
    public static Initial getInitialState(){
        return new Initial();
    }

    public static TourLoaded getTourLoaded() throws NoDataException {
        GPXToTour tourParser = new GPXToTour();
        tourParser.read("Files/GPX/TOUR/artificial.gpx");
        Tour tour = tourParser.getTour();
        return new TourLoaded(tour);
    }

    public static TrackLoaded getTrackLoaded() throws NoDataException{
        GPXToTrack trackParser = new GPXToTrack();
        trackParser.read("Files/GPX/TOUR/artificial.gpx");
        Track track = trackParser.getTrack();
        return new TrackLoaded(track);
    }
}
