package GPXrechner.Application.States;

import GPXrechner.Domain.WayModel.WayModel.Entities.Path;
import GPXrechner.Domain.WayModel.WayModel.Entities.Track;

public class TrackLoaded implements State {
    Track track;

    public TrackLoaded(Track track) {
        this.track = track;
    }

    @Override
    public Path getPath() {
        return track;
    }
}
