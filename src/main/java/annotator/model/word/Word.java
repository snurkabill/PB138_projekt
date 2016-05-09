package annotator.model.word;


public class Word {

    private final String typeId;
    private final String word;
    private final Boolean isNoise;

    public Word(String typeId, String word, boolean isNoise) {
        this.typeId = typeId;
        this.word = word;
        this.isNoise = isNoise;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getWord() {
        return word;
    }

    public Boolean isNoise() {
        return isNoise;
    }
}
