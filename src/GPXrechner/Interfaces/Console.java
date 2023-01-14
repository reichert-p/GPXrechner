package GPXrechner.Interfaces;

import GPXrechner.Application.Instructions.*;
import GPXrechner.Application.States.Initial;
import GPXrechner.Application.States.State;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;

public class Console {

    State state;
    Instruction[] instructions;

    public Console(){
        this.state = new Initial();
        generateInstructions();
        handler();
    }

    private void generateInstructions(){ //allowed instructions are implemented here
        instructions = new Instruction[]{
                new GenerateTrack(),
                new GetElevationProfile(),
                new GetDistance(),
                new GetAltitudeDifference(),
                new GetPMS(),
                new GetSpeedProfile(),
                new PMSFromFiles(),
                new PredictTime(),
                new GetTimeTaken(),
                new ReadPath(),
                new SplitTour()
        };
    }

    private void handler() {
        while (true){
            Instruction nextInstruction = ConsoleParsing.getInstruction(instructions);
            try {
                this.state = nextInstruction.execute(state);
            }catch (InvalidStateException e){
                e.printStackTrace();
            }
        }
    }
}
