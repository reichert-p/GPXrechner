import Entities.Tour;
import WayModel.TourPoint;

public class Main {
    public static void main(String[] args) {
        Tour t = new Tour("wilde Tour");
        t.addTourPoints(new TourPoint[]{new TourPoint(1,2,1,null),new TourPoint(2,4,2,null)});
        System.out.println(t);
    }
}