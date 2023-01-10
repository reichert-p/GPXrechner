package GPXrechner.Application.States;

import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.Entities.Track;

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
