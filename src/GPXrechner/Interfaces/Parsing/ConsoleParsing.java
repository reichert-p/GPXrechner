package GPXrechner.Interfaces.Parsing;

import GPXrechner.Application.Instructions.Instruction;
import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Interfaces.Output.UserOutput;
import GPXrechner.Interfaces.Parsing.GPXReader.NoDataException;
import GPXrechner.Interfaces.Parsing.GPXReader.GPXToTour;
import GPXrechner.WayModel.Entities.Tour;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleParsing implements UserInput{
    UserOutput userOutput;
    public ConsoleParsing(UserOutput userOutput){
        this.userOutput = userOutput;
    }

    public String readPath(){
        Scanner scanner = new Scanner(System.in);
        userOutput.userToGiveClassPath("Files/GPX");
        return "Files\\GPX\\" + scanner.next();
    }

    public String[] readPaths() {
        List<String> output = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        userOutput.userToGiveClassPath("Files/GPX", "x");
        while (true){
            String in = scanner.next();
            if (in.matches("x")){
                return output.toArray(new String[0]);
            }
            output.add("Files\\GPX\\" + in);
        }
    }

    public Instruction getInstruction(Instruction[] instructions){
        Scanner scanner = new Scanner(System.in);
        userOutput.userToProvideInstruction();
        String userInput = scanner.nextLine();
        for (Instruction i:instructions) {
            if (userInput.matches(i.getRegex())){
                return i;
            }
        }
        userOutput.provideInstructions(instructions);
        return getInstruction(instructions);
    }

    public int getGranularity(){
        Scanner scanner = new Scanner(System.in);
        userOutput.userToProvideGranularity();
        return scanner.nextInt();
    }

    public MovementSpeed parseMovementSpeed() {
        Scanner scanner = new Scanner(System.in);
        userOutput.userToProvideMovementSpeed();
        String userInput = scanner.next();
        if (userInput.matches("PMS")){
            try {
                return pathsToMovementSpeeds();
            } catch (InsufficientDataException e) {
                e.printStackTrace();
            }
        }
        for (Sport sport:Sport.values()) {
            if (userInput.matches(sport.name().toLowerCase())){
                return sport;
            }
        }
        userOutput.provideSports();
        return parseMovementSpeed();
    }

    public MovementSpeed pathsToMovementSpeeds() throws InsufficientDataException {
        GPXToTour tourParser = new GPXToTour();
        String[] paths = this.readPaths();
        List<Tour> tours= new ArrayList<>();
            for (String s:paths) {
            try {
                tourParser.read(s);
                tours.add(tourParser.getTour());
            }catch (NoDataException e){
                userOutput.alertWrongFileType(s, "tour");
            }
        }
        return SpeedCalculator.predictPersonalMovementSpeed(tours.toArray(Tour[]::new));
    }

    public EvaluationFunction parseEvaluationFunction(EvaluationFunction[] evaluationFunctions) {
        Scanner scanner = new Scanner(System.in);
        userOutput.info("Please provide evaluation function");
        String userInput = scanner.nextLine();
        for (EvaluationFunction i:evaluationFunctions) {
            if (userInput.matches(i.getRegex())){
                return i;
            }
        }
        userOutput.provideEvaluationFunctions(evaluationFunctions);
        return parseEvaluationFunction(evaluationFunctions);
    }

    public Duration parseMaxDuration() {
        Scanner scanner = new Scanner(System.in);
        Duration output;
        userOutput.info("Bitte maximale Gehzeit in Stunden / Minuten eingeben. Stunden:");
        int temp = 0;
        try {
            temp = scanner.nextInt();
        }catch (Exception e){

        }
        output = Duration.ofHours(temp);
        userOutput.info("minuten");
        try {
            temp = scanner.nextInt();
        }catch (Exception e){
            temp = 0;
        }
        output = output.plus(Duration.ofMinutes(temp));
        return output;
    }
}
