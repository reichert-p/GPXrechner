package GPXrechner.Domain.Calculations;

public class InsufficientDataException extends Throwable {
    public InsufficientDataException() {
        super("Data insufficient data for Operation");
    }
}
