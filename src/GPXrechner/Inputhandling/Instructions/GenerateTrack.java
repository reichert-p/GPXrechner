package GPXrechner.Inputhandling.Instructions;

import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Entities.Track;
import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.States.State;
import GPXrechner.Inputhandling.States.TourLoaded;
import GPXrechner.Inputhandling.States.TrackLoaded;

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
