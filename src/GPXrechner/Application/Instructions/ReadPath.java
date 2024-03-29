package GPXrechner.Application.Instructions;

import GPXrechner.Application.States.State;
import GPXrechner.Application.States.TourLoaded;
import GPXrechner.Application.States.TrackLoaded;
import GPXrechner.Domain.WayModel.Entities.Tour;
import GPXrechner.Domain.WayModel.Entities.Track;
import GPXrechner.Plugin.InvalidStateException;
import GPXrechner.Application.UserOutput;
import GPXrechner.Plugin.Parsing.GPXReader.GPXToTour;
import GPXrechner.Plugin.Parsing.GPXReader.GPXToTrack;
import GPXrechner.Plugin.Parsing.GPXReader.NoDataException;
import GPXrechner.Application.UserInput;

public class ReadPath implements Instruction {
    UserOutput userOutput;
    UserInput userInput;

    public ReadPath(UserOutput userOutput, UserInput userInput) {
        this.userOutput = userOutput;
        this.userInput = userInput;
    }

    @Override
    public String getDescription() {
        return "define a path for an gpx file to be parsed";
    }

    @Override
    public State execute(State state) throws InvalidStateException {
        while (true) {
            String s = userInput.readPath();
            try {
                GPXToTour tourParser = new GPXToTour();
                tourParser.read(s);
                Tour tour = tourParser.getTour();
                return new TourLoaded(tour);
            } catch (NoDataException e) {
                try {
                    GPXToTrack trackParser = new GPXToTrack();
                    trackParser.read(s);
                    Track track = trackParser.getTrack();
                    return new TrackLoaded(track);
                } catch (NoDataException f) {
                    userOutput.alertWrongFileType(s, "gpx path");
                }
            }
        }
    }

    @Override
    public String getRegex() {
        return "load gpx";
    }
}
