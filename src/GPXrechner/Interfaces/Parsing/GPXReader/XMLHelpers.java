package GPXrechner.Interfaces.Parsing.GPXReader;

import GPXrechner.WayModel.TrackPoint;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class XMLHelpers {
    public static LocalDateTime parseDate(String s) {
        s = s.replaceAll("[Z]", ""); //The Z from xsd:dateTime cant be handled by LocalDateTime.parse
        LocalDateTime localDateTime = LocalDateTime.parse(s);
        return localDateTime;
    }


    public static String getName(Document doc) {
        NodeList names = doc.getElementsByTagName("name");
        if (names.getLength() > 0) {
            return names.item(0).getTextContent();
        }
        return "";
    }

    public static Document documentFactory(String pathName) {
        try {
            File file = new File(pathName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static TrackPoint parseTrackPoint(Node trackNode) {
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
