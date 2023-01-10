package GPXrechner.Interfaces.Parsing;

import GPXrechner.Calculations.InsufficientDataException;
import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.SpeedCalculator;
import GPXrechner.Application.Instructions.Instruction;
import GPXrechner.WayModel.Entities.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleParsing {

    public static String readPath(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte Pfad angeben(relativ zum Files/GPX Ordner)!");
        return "Files\\GPX\\" + scanner.next();
    }

    public static String[] readPaths() {
        List<String> output = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte Pfade angeben(relativ zum Files/GPX Ordner)! Zum Abbrechen x drücken");
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
        System.out.println("Bitte Befehl eingeben");
        String userInput = scanner.nextLine();
        for (Instruction i:instructions) {
            if (userInput.matches(i.getRegex())){
                return i;
            }
        }
        printInstructions(instructions);
        return getInstruction(instructions);
    }

    private static void printInstructions(Instruction[] instructions){ //das vielleicht in help Instruction?
        System.out.println("Instructions are");
        for (Instruction i: instructions) {
            System.out.println(i.getDescription() + " (" + i.getRegex() + ")");
        }
    }

    public static int getGranularity(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie fein soll die x-Achse werden(Ganzzahl der Punkte)?");
        return scanner.nextInt();
    }

    public static MovementSpeed parseMovementSpeed() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte Sportart eingeben. Alternativ 'PMS' um Geschwindigkeit aus Tour(en) zu berechnen");
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
        printSports();
        return parseMovementSpeed();
    }

    private static void printSports(){
        System.out.println("Available Sports are");
        for (Sport sport:Sport.values()) {
            System.out.println(sport.name().toLowerCase());
        }
    }

    public static MovementSpeed pathsToMovementSpeeds() throws InsufficientDataException {
        XMLParser xmlParser = new DOMParser();
        String[] paths = ConsoleParsing.readPaths();
        List<Tour> tours= new ArrayList<>();
            for (String s:paths) {
            try {
                tours.add(xmlParser.parseTour(s));
            }catch (NoTourException e){
                System.out.println(s + "does not lead to a tour");
            }
        }
        return SpeedCalculator.predictPersonalMovementSpeed(tours.toArray(Tour[]::new));
    }

}
