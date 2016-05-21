package annotator.model.pack;

import org.bson.Document;

import java.util.ArrayList;

public class Package {

    private final String id;
    private final String typeId;
    private final Integer wordCount;
    private final ArrayList<String> wordList;

    public Package(
        String id,
        String typeId,
        ArrayList<String> wordList
    ) {
        this.id = id;
        this.typeId = typeId;
        this.wordCount = wordList.size();
        this.wordList = wordList;
    }

    public Package(Document document) {
        this(
            document.getObjectId("_id").toString(),
            document.getString("type_id"),
            (ArrayList<String>) document.get("words")
        );
    }

    public String getId() {
        return this.id;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public Integer getWordCount() {
        return this.wordCount;
    }

    public ArrayList<String> getWordList() {
        return this.wordList;
    }
}
