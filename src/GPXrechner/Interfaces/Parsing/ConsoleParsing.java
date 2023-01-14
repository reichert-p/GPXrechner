package GPXrechner.Interfaces.Parsing;

import GPXrechner.Application.Instructions.Instruction;
import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Interfaces.Output.ConsoleInformation;
import GPXrechner.WayModel.Entities.Tour;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleParsing {

    public static String readPath(){
        Scanner scanner = new Scanner(System.in);
        ConsoleInformation.userToGiveClassPath("Files/GPX");
        return "Files\\GPX\\" + scanner.next();
    }

    public static String[] readPaths() {
        List<String> output = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        ConsoleInformation.userToGiveClassPath("Files/GPX", "x");
        while (true){
            String in = scanner.next();
            if (in.matches("x")){
                return output.toArray(new String[0]);
            }
            output.add("Files\\GPX\\" + in);
        }
    }

    public static Instruction getInstruction(Instruction[] instructions){
        Scanner scanner = new Scanner(System.in);
        ConsoleInformation.userToProvideInstruction();
        String userInput = scanner.nextLine();
        for (Instruction i:instructions) {
            if (userInput.matches(i.getRegex())){
                return i;
            }
        }
        ConsoleInformation.provideInstructions(instructions);
        return getInstruction(instructions);
    }

    public static int getGranularity(){
        Scanner scanner = new Scanner(System.in);
        ConsoleInformation.userToProvideGranularity();
        return scanner.nextInt();
    }

    public static MovementSpeed parseMovementSpeed() {
        Scanner scanner = new Scanner(System.in);
        ConsoleInformation.userToProvideMovementSpeed();
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
        ConsoleInformation.provideSports();
        return parseMovementSpeed();
    }

    public static MovementSpeed pathsToMovementSpeeds() throws InsufficientDataException {
        XMLParser xmlParser = new DOMParser();
        String[] paths = ConsoleParsing.readPaths();
        List<Tour> tours= new ArrayList<>();
            for (String s:paths) {
            try {
                tours.add(xmlParser.parseTour(s));
            }catch (NoTourException e){
                ConsoleInformation.alertWrongFileType(s, "tour");
            }
        }
        return SpeedCalculator.predictPersonalMovementSpeed(tours.toArray(Tour[]::new));
    }

    public static EvaluationFunction parseEvaluationFunction(EvaluationFunction[] evaluationFunctions) {
        Scanner scanner = new Scanner(System.in);
        ConsoleInformation.info("Please provide evaluation function");
        String userInput = scanner.nextLine();
        for (EvaluationFunction i:evaluationFunctions) {
            if (userInput.matches(i.getRegex())){
                return i;
            }
        }
        ConsoleInformation.provideEvaluationFunctions(evaluationFunctions);
        return parseEvaluationFunction(evaluationFunctions);
    }

    public static Duration parseMaxDuration() {
        Scanner scanner = new Scanner(System.in);
        Duration output;
        ConsoleInformation.info("Bitte maximale Gehzeit in Stunden / Minuten eingeben. Stunden:");
        int temp = 0;
        try {
            temp = scanner.nextInt();
        }catch (Exception e){

        }
        output = Duration.ofHours(temp);
        ConsoleInformation.info("minuten");
        try {
            temp = scanner.nextInt();
        }catch (Exception e){
            temp = 0;
        }
        output = output.plus(Duration.ofMinutes(temp));
        return output;
    }
}
