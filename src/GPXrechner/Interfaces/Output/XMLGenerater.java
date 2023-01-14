package GPXrechner.Interfaces.Output;

import GPXrechner.WayModel.Entities.Track;

import javax.xml.parsers.ParserConfigurationException;

public interface XMLGenerater {
    void generateGPX(Track track, String fileLocation) throws ParserConfigurationException;
}
