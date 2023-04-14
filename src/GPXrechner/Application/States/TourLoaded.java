package GPXrechner.Application.States;

import GPXrechner.Domain.WayModel.Entities.Path;
import GPXrechner.Domain.WayModel.Entities.Tour;

public class TourLoaded implements State {

    Tour tour;

    public TourLoaded(Tour tour) {
        this.tour = tour;
    }

    @Override
    public Path getPath() {
        return tour;
    }
}
