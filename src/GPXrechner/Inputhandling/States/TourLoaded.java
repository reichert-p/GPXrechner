package GPXrechner.Inputhandling.States;

import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.Entities.Tour;

public class TourLoaded implements State{

    public TourLoaded(Tour tour) {
        this.tour = tour;
    }
    Tour tour;

    @Override
    public Path getPath() {
        return tour;
    }
}
