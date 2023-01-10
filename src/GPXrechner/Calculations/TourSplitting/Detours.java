package GPXrechner.Calculations.TourSplitting;

import GPXrechner.WayModel.WayPoint;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Detours {

    List<Detour> possibleDetours;

    public Detours() {
        possibleDetours = new ArrayList<>();
    }

    public void addDetour(Detour detour) {
        possibleDetours.add(detour);
    }

    public void addDetour(int position, WayPoint destination, Duration expenditure) {
        possibleDetours.add(new Detour(position, destination,expenditure));
    }

    public List<Detour> getPossibleDetours() {
        return possibleDetours;
    }

    static class Detour{
        int position;
        WayPoint destination;
        Duration expenditure;

        public Detour(int position, WayPoint destination, Duration expenditure) {
            this.position = position;
            this.destination = destination;
            this.expenditure = expenditure;
        }

        public int getPosition() {
            return position;
        }

        public WayPoint getDestination() {
            return destination;
        }
    }
}
