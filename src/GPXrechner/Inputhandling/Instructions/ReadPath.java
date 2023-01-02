package GPXrechner.Inputhandling.Instructions;

import GPXrechner.Inputhandling.InvalidStateException;
import GPXrechner.Inputhandling.Parsing.*;
import GPXrechner.Inputhandling.States.State;
import GPXrechner.Inputhandling.States.TourLoaded;
import GPXrechner.Inputhandling.States.TrackLoaded;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Entities.Track;

public class ReadPath implements Instruction{
    @Override
    public String getDescription() {
        return "define a path for an gpx file to be parsed";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        XMLParser XMLParser = new DOMParser();
        while (true){
            String s = ConsoleParsing.readPath();
            try {
                Tour tour = XMLParser.parseTour(s);
                return new TourLoaded(tour);
            }catch (NoTourException e){
                try {
                    Track track = XMLParser.parseTrack(s);
                    return new TrackLoaded(track);
                }catch (NoTrackException f){
                    f.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getRegex() {
        return "load gpx";
    }
}
