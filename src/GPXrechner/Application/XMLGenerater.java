package GPXrechner.Application;

import GPXrechner.Domain.WayModel.WayModel.Entities.Track;

import javax.xml.parsers.ParserConfigurationException;

public interface XMLGenerater {
    void generateGPX(Track track, String fileLocation) throws ParserConfigurationException;
}
