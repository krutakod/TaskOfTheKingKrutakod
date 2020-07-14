import lombok.NonNull;

public class TreeException extends Exception {

    private static final String STD_EX = "Unidentified Exception";

    public TreeException(@NonNull String message) {
        super(message);
    }

    public TreeException() {
        super(STD_EX);
    }
}
