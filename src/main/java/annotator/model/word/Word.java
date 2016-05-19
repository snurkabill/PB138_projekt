package annotator.model.word;


public class Word {

    private final String id;
    private final String typeId;
    private final String word;
    private final Boolean belongsToType;

    public Word(String id, String typeId, String word, boolean belongsToType) throws WordNotFoundException {
        if (id == null)
            throw new WordNotFoundException("Word: id not found");
        this.id = id;
        this.typeId = typeId;
        this.word = word;
        this.belongsToType = belongsToType;
    }

    public Word(String id, String typeId, String word) throws WordNotFoundException {
        if (id == null)
            throw new WordNotFoundException("Word: id not found");
        this.id = id;
        this.typeId = typeId;
        this.word = word;
        this.belongsToType = null;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getWord() {
        return word;
    }

    public Boolean belongsToType() {
        return belongsToType;
    }

    public String getId() {
        return id;
    }

}
