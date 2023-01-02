package GPXrechner.Inputhandling.Parsing;

import GPXrechner.Inputhandling.Instructions.Instruction;

import java.util.Scanner;

public class ConsoleParsing {

    public static String readPath(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte Pfad angeben!"); //TODO input sanitazation
        return scanner.next();
    }

    public static String[] readPaths() {
        return new String[]{"TODO"};//TODO implement this
    }

    public static Instruction getInstruction(Instruction[] instructions){
        return instructions[0]; //TODO implementation
    }
}
