package GPXrechner.Inputhandling;

import GPXrechner.Inputhandling.Instructions.*;
import GPXrechner.Inputhandling.Parsing.ConsoleParsing;
import GPXrechner.Inputhandling.States.Initial;
import GPXrechner.Inputhandling.States.State;

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
