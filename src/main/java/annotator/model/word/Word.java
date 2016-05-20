package annotator.model.word;

import org.bson.Document;

public class Word {

    private final String id;
    private final String typeId;
    private final String word;
    private final Boolean belongsToType;

    public Word(String id, String typeId, String word, Boolean belongsToType) {
        this.id = id;
        this.typeId = typeId;
        this.word = word;
        this.belongsToType = belongsToType;
    }

    public Word(Document document) {
        this(
            document.getObjectId("_id").toString(),
            document.getString("type_id"),
            document.getString("word"),
            document.getBoolean("belongs_to_type")
        );
    }

    public String getTypeId() {
        return this.typeId;
    }

    public String getWord() {
        return this.word;
    }

    public Boolean belongsToType() {
        return this.belongsToType;
    }

    public String getId() {
        return this.id;
    }

}
