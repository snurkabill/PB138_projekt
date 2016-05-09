package annotator.model.word;


public class Word {

    private final String typeId;
    private final String word;
    private final boolean isNoise;

    public Word(String typeId, String word, boolean isNoise) {
        this.typeId = typeId;
        this.word = word;
        this.isNoise = isNoise;
    }
}
