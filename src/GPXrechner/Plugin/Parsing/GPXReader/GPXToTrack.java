package GPXrechner.Plugin.Parsing.GPXReader;

import GPXrechner.Application.XMLParser;
import GPXrechner.Domain.WayModel.Entities.Track;
import GPXrechner.Domain.WayModel.TrackPoint;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GPXToTrack implements XMLParser {
    Track track;

    @Override
    public void read(String path) throws NoDataException {
        try {
            Document doc = XMLHelpers.documentFactory(path);
            String name = XMLHelpers.getName(doc);
            Track track = new Track(name);
            NodeList nodeList = doc.getElementsByTagName("trkpt");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                TrackPoint trackPoint = XMLHelpers.parseTrackPoint(node);
                track.addTrackPoint(trackPoint);
            }
            this.track = track;
        } catch (Exception e) {
            throw new NoDataException();
        }
    }

    public Track getTrack() {
        return this.track;
    }
}
