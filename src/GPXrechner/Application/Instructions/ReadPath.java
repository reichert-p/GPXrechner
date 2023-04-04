package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Interfaces.InvalidStateException;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.Interfaces.Parsing.*;
import GPXrechner.Interfaces.Parsing.GPXReader.GPXToTour;
import GPXrechner.Interfaces.Parsing.GPXReader.GPXToTrack;
import GPXrechner.Interfaces.Parsing.GPXReader.NoDataException;
import GPXrechner.WayModel.Entities.Tour;
import GPXrechner.WayModel.Entities.Track;

public class ReadPath implements Instruction{
    @Override
    public String getDescription() {
        return "define a path for an gpx file to be parsed";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        while (true){
            String s = ConsoleParsing.readPath();
            try {
                GPXToTour tourParser = new GPXToTour();
                tourParser.read(s);
                Tour tour = tourParser.getTour();
                return new TourLoaded(tour);
            }catch (NoDataException e){
                try {
                    GPXToTrack trackParser = new GPXToTrack();
                    trackParser.read(s);
                    Track track = trackParser.getTrack();
                    return new TrackLoaded(track);
                }catch (NoDataException f){
                    ConsoleInformation.alertWrongFileType(s, "gpx path");
                }
            }
        }
    }

    @Override
    public String getRegex() {
        return "load gpx";
    }
}
