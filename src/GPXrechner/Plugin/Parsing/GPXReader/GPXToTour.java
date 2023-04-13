package GPXrechner.Plugin.Parsing.GPXReader;

import GPXrechner.Application.XMLParser;
import GPXrechner.Domain.WayModel.WayModel.Entities.Tour;
import GPXrechner.Domain.WayModel.WayModel.TourPoint;
import GPXrechner.Domain.WayModel.WayModel.TrackPoint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GPXToTour implements XMLParser {

    Tour tour;

    @Override
    public void read(String path) throws NoDataException {
        try {
            Document doc = XMLHelpers.documentFactory(path);
            String name = XMLHelpers.getName(doc);
            Tour tour = new Tour(name);
            NodeList nodeList = doc.getElementsByTagName("trkpt");
            for (int itr = 0; itr < nodeList.getLength(); itr++) { // alle trkpts durchlaufen
                Node node = nodeList.item(itr);
                TrackPoint trackPoint = XMLHelpers.parseTrackPoint(node);
                Element eElement = (Element) node;
                tour.addTourPoint(
                        new TourPoint(
                                trackPoint,
                                XMLHelpers.parseDate(eElement.getElementsByTagName("time").item(0).getTextContent())
                        ));
            }
            this.tour = tour;
        } catch (Exception e) {
            throw new NoDataException();
        }
    }

    public Tour getTour() {
        return this.tour;
    }
}
