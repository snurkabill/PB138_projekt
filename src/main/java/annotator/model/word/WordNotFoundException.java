package annotator.model.word;

public class WordNotFoundException extends Exception {

    public WordNotFoundException(String wordId) {
        super(String.format(
            "Word %s not found.",
            wordId
        ));
    }
}
