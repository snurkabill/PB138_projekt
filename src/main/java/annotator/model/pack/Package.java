package annotator.model.pack;

import org.bson.Document;

import java.util.ArrayList;

public class Package {

    private final String id;
    private final String typeId;
    private final String name;
    private final Integer wordCount;
    private final ArrayList<String> wordList;

    public Package(
        String id,
        String typeId,
        String name,
        ArrayList<String> wordList
    ) {
        this.id = id;
        this.typeId = typeId;
        this.name = name;
        this.wordCount = wordList.size();
        this.wordList = wordList;
    }

    public Package(Document document) {
        this(
            document.getObjectId("_id").toString(),
            document.getString("type_id"),
            document.getString("name"),
            (ArrayList<String>) document.get("words")
        );
    }

    public String getId() {
        return this.id;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public String getName() {
        return this.name;
    }

    public Integer getWordCount() {
        return this.wordCount;
    }

    public ArrayList<String> getWordList() {
        return this.wordList;
    }
}
