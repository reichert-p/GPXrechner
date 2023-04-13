package GPXrechner.Interfaces;

import GPXrechner.Application.Instructions.*;
import GPXrechner.Application.States.Initial;
import GPXrechner.Application.States.State;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.Interfaces.Output.UserOutput;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;
import GPXrechner.Interfaces.Parsing.UserInput;

public class Console {

    State state;
    Instruction[] instructions;

    UserOutput consoleInformation;
    UserInput consoleParsing;

    public Console(){
        this.state = new Initial();
        this.consoleInformation = new ConsoleInformation();
        this.consoleParsing = new ConsoleParsing(consoleInformation);
        generateInstructions();
        handler();
    }

    private void generateInstructions(){ //allowed instructions are implemented here
        instructions = new Instruction[]{
                new Exit(),
                new GenerateTrack(consoleInformation),
                new GetAltitudeDifference(consoleInformation),
                new GetDistance(consoleInformation),
                new GetElevationProfile(consoleInformation,consoleParsing),
                new GetPMS(consoleInformation),
                new GetSpeedProfile(consoleInformation,consoleParsing),
                new GetTimeTaken(consoleInformation),
                new PMSFromFiles(consoleInformation,consoleParsing),
                new PredictTime(consoleInformation,consoleParsing),
                new ReadPath(consoleInformation,consoleParsing),
                new SplitTour(consoleInformation,consoleParsing),

        };
    }

    private void handler() {
        while (true){
            Instruction nextInstruction = consoleParsing.getInstruction(instructions);
            try {
                this.state = nextInstruction.execute(state);
            }catch (InvalidStateException e){
                e.printStackTrace();
            }
        }
    }
}
