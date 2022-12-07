import Calculations.DistanceCalculator;
import Entities.Tour;
import WayModel.TourPoint;
import WayModel.WayPoint;

public class Main {
    public static void main(String[] args) {
        WayPoint w1 = new WayPoint(45,45, 100,"Kaiserallee");
        WayPoint w2 = new WayPoint(45.00,45.01, 100,"Radolfzell");

        System.out.println(DistanceCalculator.calc2dDistance(w1,w2));
    }
}