package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Entities.Track;

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
            ConsoleInformation.infoGeneratedTrack(track.toString());
            return new TrackLoaded(track);
        }
        throw new InvalidStateException("TourLoaded",state);
    }

    @Override
    public String getRegex() {
        return "generate track";
    }
}
//TODO Test vervollständigen
