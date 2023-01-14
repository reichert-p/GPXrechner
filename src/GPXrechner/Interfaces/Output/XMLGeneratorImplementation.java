package GPXrechner.Interfaces.Output;

import GPXrechner.WayModel.Entities.Track;
import GPXrechner.WayModel.Location;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class XMLGeneratorImplementation implements XMLGenerater{
    @Override
    public void generateGPX(Track track, String fileLocation) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element root = doc.createElement("gpx");
        doc.appendChild(root);
        Element metadata = doc.createElement("metadata");
        root.appendChild(metadata);

        Element name = doc.createElement("name");
        metadata.appendChild(name);
        name.setTextContent(track.toString());
        Element desc = doc.createElement("desc");
        metadata.appendChild(desc);
        desc.setTextContent("File automatically created by Philipp Reichert's GPXCalculator");

        Element trk = doc.createElement("trk");
        root.appendChild(trk);
        Element trkSeg = doc.createElement("trkseg");
        trk.appendChild(trkSeg);
        for (Location trackPoint: track.getOrderedLocations()) {
            Element trkpt = doc.createElement("trkpt");
            trkpt.setAttribute("lat", String.valueOf(trackPoint.getLat().getValue()));
            trkpt.setAttribute("lon", String.valueOf(trackPoint.getLon().getValue()));
            Element ele = doc.createElement("ele");
            trkpt.appendChild(ele);
            ele.setTextContent(String.valueOf(trackPoint.getEle().getValue()));
            trkSeg.appendChild(trkpt);
        }
        try (FileOutputStream output = new FileOutputStream(fileLocation)) {
            writeXml(doc, output);
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void writeXml(Document doc, OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
