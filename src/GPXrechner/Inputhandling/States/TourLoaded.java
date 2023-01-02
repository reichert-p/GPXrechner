package GPXrechner.Inputhandling.States;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Entities.Path;
import GPXrechner.Entities.Tour;

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
