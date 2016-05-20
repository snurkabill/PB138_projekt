package annotator.model.pack;

import java.util.ArrayList;

public class Package {

    private final String id;
    private final String typeId;
    private final Integer wordCount;
    private final ArrayList<String> wordList;


    public Package(String id, String typeId, Integer wordCount, ArrayList<String> wordList) throws PackageNotFoundException {
        if (id == null)
            throw new PackageNotFoundException("Package: id not found");
        this.id = id;
        this.typeId = typeId;
        this.wordCount = wordCount;
        this.wordList = wordList;
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
