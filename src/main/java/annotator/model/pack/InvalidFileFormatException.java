package annotator.model.pack;

public class InvalidFileFormatException extends Exception {

    private Integer line;

    public InvalidFileFormatException(String message, Integer line) {
        super(message);
        this.line = line;
    }

    public InvalidFileFormatException(String message) {
        this(message, null);
    }

    public Integer getLine() {
        return this.line;
    }
}
