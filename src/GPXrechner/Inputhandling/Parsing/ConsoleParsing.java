package GPXrechner.Inputhandling.Parsing;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Inputhandling.Instructions.Instruction;

import java.util.Scanner;

public class ConsoleParsing {

    public static String readPath(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte Pfad angeben(relativ zum Files/GPX Ordner)!"); //TODO input sanitazation
        return "Files\\GPX\\" + scanner.next();
    }

    public static String[] readPaths() {
        return new String[]{"TODO"};//TODO implement this
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

    public static int getGranularity() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie fein soll die x-Achse werden(Ganzzahl der Punkte)?"); //TODO input sanitazation
        return scanner.nextInt();
    }

    public static MovementSpeed parseMovementSpeed() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte Sportart eingeben");
        String userInput = scanner.next();
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
}
