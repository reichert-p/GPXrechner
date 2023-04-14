package GPXrechner.Plugin.Parsing.GPXReader;

import GPXrechner.Application.XMLParser;
import GPXrechner.Domain.Calculations.TourSplitting.WayPointSet;
import GPXrechner.Domain.WayModel.TrackPoint;
import GPXrechner.Domain.WayModel.WayPoint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class GPXToWayPointSet implements XMLParser {
    WayPointSet wayPointSet;

    @Override
    public void read(String path) throws NoDataException {
        Document doc = XMLHelpers.documentFactory(path);
        String name = XMLHelpers.getName(doc);
        WayPointSet wayPointSet = new WayPointSet(name);
        NodeList nodeList = doc.getElementsByTagName("wpt");
        if (nodeList.getLength() == 0) {
            throw new NoDataException();
        }
        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            TrackPoint trackPoint = XMLHelpers.parseTrackPoint(node);
            Element eElement = (Element) node;
            String nodeDescr;
            try {
                nodeDescr = eElement.getElementsByTagName("name").item(0).getTextContent();
            } catch (Exception e) {
                nodeDescr = "Unnamed Entity";
            }
            wayPointSet.addWayPoint(
                    new WayPoint(
                            trackPoint,
                            nodeDescr
                    )
            );
        }
        this.wayPointSet = wayPointSet;
    }

    public WayPointSet getWayPointSet() {
        return this.wayPointSet;
    }
}
