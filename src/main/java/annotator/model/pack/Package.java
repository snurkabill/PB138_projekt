package annotator.model.pack;

import java.util.List;

public class Package {

    private final String typeId;
    private final Integer wordCount;
    private final List<String> wordList;


    public Package(String typeId, Integer wordCount, List<String> wordList) {
        this.typeId = typeId;
        this.wordCount = wordCount;
        this.wordList = wordList;
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
