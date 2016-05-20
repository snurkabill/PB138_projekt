package annotator.model.type;

import org.bson.Document;

public class Type {

    private final String id;
    private final String type;

    public Type(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public Type(Document document) {
        this(
            document.getObjectId("_id").toString(),
            document.getString("type")
        );
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }
}
