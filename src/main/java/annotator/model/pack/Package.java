package annotator.model.pack;

import java.util.List;

public class Package {

    private final String id;
    private final String typeId;
    private final Integer wordCount;
    private final List<String> wordList;


    public Package(String id,String typeId, Integer wordCount, List<String> wordList) throws PackageNotFoundException {
        if (id == null)
            throw new PackageNotFoundException("Package: id not found");
        this.id = id;
        this.typeId = typeId;
        this.wordCount = wordCount;
        this.wordList = wordList;
    }

    public String getId() {
        return id;
    }

    public String getTypeId() {
        return typeId;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public List<String> getWordList() {
        return wordList;
    }
}
