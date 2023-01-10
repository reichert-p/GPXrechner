package GPXrechner.Interfaces;

import GPXrechner.Application.Instructions.*;
import GPXrechner.Interfaces.Parsing.ConsoleParsing;
import GPXrechner.Application.States.Initial;
import GPXrechner.Application.States.State;

public class Console {

    State state;
    Instruction[] instructions;

    public Console(){
        this.state = new Initial();
        generateInstructions();
        handler();
    }

    private void generateInstructions(){
        instructions = new Instruction[]{
                new GenerateTrack(),
                new GetElevationProfile(),
                new GetDistance(),
                new GetHeigthDifference(),
                new GetPMS(),
                new GetSpeedProfile(),
                new PMSFromFiles(),
                new PredictTime(),
                new GetTimeTaken(),
                new ReadPath()
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
