package GPXrechner.Interfaces;

import GPXrechner.Application.States.State;

public class InvalidStateException extends Exception {
    public InvalidStateException(String expected, State actual){
        super("Expected " + expected + " but got " + actual.toString());
    }
}
