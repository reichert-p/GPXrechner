package GPXrechner.Application.Instructions;

import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Entities.Track;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;

public class GenerateTrack implements Instruction {
    @Override
    public String getDescription() {
        return "generate a track from the tour";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (state instanceof TourLoaded){
            Tour tour = (Tour)state.getPath();
            Track track = tour.getTrack();
            System.out.println("Generated Track " + track.toString());
            return new TrackLoaded(track);
        }
        return null;
    }

    @Override
    public String getRegex() {
        return "generate track";
    }
} //TODO eine Ausgabe von Touren/Tracks im gpx Format
//TODO Test vervollständigen
