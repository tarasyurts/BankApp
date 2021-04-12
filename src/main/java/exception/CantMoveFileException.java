package exception;

public class CantMoveFileException extends RuntimeException {
    public CantMoveFileException(String message) {
        super(message);
    }
}
