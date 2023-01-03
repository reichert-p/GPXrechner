package GPXrechner.Inputhandling.Parsing;

import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Entities.Track;
import GPXrechner.WayModel.TourPoint;
import GPXrechner.WayModel.TrackPoint;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class DOMParser implements XMLParser{
    public Tour parseTour(String pathName) throws NoTourException{
        Tour tour = null;
        try {
            Document doc = documentFactory(pathName);
            String name = getName(doc);
            tour = new Tour(name);
            NodeList nodeList = doc.getElementsByTagName("trkpt");
            for (int itr = 0; itr < nodeList.getLength(); itr++) { // alle trkpts durchlaufen
                Node node = nodeList.item(itr);
                TrackPoint trackPoint = parseTrackPoint(node);
                Element eElement = (Element) node;
                tour.addTourPoint(
                        new TourPoint(
                                trackPoint,
                                parseDate(eElement.getElementsByTagName("time").item(0).getTextContent())
                        ));
            }
            return tour;
        }catch (Exception E){
            throw new NoTourException();
        }
    }

    public Track parseTrack(String pathName) throws NoTrackException{
        Track track = null;
        try {
            Document doc = documentFactory(pathName);
            String name = getName(doc);
            track = new Track(name);
            NodeList nodeList = doc.getElementsByTagName("trkpt");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                TrackPoint trackPoint = parseTrackPoint(node);
                Element eElement = (Element) node;
                track.addTrackPoint(trackPoint);
            }
            return track;
        }catch (Exception E){
            throw new NoTrackException();
        }
    }

    private static LocalDateTime parseDate(String s){
        s = s.replaceAll("[Z]",""); //The Z from xsd:dateTime cant be handled by LocalDateTime.parse
        LocalDateTime localDateTime = LocalDateTime.parse(s);
        return localDateTime;
    }


    private static String getName(Document doc){
        NodeList names = doc.getElementsByTagName("name");
        if(names.getLength() > 0){
            return names.item(0).getTextContent();
        }
        return "";
    }

    private static Document documentFactory(String pathName) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(pathName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        return doc;
    }

    public static TrackPoint parseTrackPoint(Node trackNode){
        NamedNodeMap attributes = trackNode.getAttributes();
        Node lat = attributes.getNamedItem("lat");
        Node lon = attributes.getNamedItem("lon");
        Element eElement = (Element) trackNode;
        return new TrackPoint(
                        Double.valueOf(lat.getTextContent()),
                        Double.valueOf(lon.getTextContent()),
                        Double.valueOf(eElement.getElementsByTagName("ele").item(0).getTextContent())
                );
    }
}
