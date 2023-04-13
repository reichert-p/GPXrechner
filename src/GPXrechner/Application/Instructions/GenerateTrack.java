package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.UserOutput;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Entities.Track;

public class GenerateTrack implements Instruction {
    UserOutput userOutput;

    public GenerateTrack(UserOutput userOutput) {
        this.userOutput = userOutput;
    }

    @Override
    public String getDescription() {
        return "generate a track from a tour";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        if (state instanceof TourLoaded) {
            Tour tour = (Tour) state.getPath();
            Track track = tour.getTrack();
            userOutput.infoGeneratedTrack(track.toString());
            return new TrackLoaded(track);
        }
        throw new InvalidStateException("TourLoaded", state);
    }

    @Override
    public String getRegex() {
        return "generate track";
    }
}
