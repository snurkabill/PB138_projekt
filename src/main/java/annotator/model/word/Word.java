package annotator.model.word;


public class Word {

    private final String id;
    private final String typeId;
    private final String word;
    private final Boolean isNoise;

    public Word(String id, String typeId, String word, boolean isNoise) throws WordNotFoundException {
        if (id == null)
            throw new WordNotFoundException("Word: id not found");
        this.id = id;
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

    public String getId() {
        return id;
    }

}
