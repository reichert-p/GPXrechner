package GPXrechner.Inputhandling.States;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Entities.Path;
import GPXrechner.Entities.Track;

public class TrackLoaded implements State{
    Track track;

    public TrackLoaded(Track track) {
        this.track = track;
    }

    @Override
    public Path getPath() {
        return track;
    }
}
