package annotator.model.word;


import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCreator {

    private final MongoCollection<Document> words;

    public WordCreator(MongoDatabase database) {
        this.words = database.getCollection("words");
    }

    public List<String> createMany(String typeId, Map<String, Boolean> words) {
        List<Document> wordList = words
            .entrySet()
            .stream()
            .map(
                word -> new Document()
                    .append("type_id", typeId)
                    .append("word", word.getKey())
                    .append("belongs_to_type", word.getValue())
            )
            .collect(Collectors.toList());

        this.words.insertMany(wordList);

        return wordList
            .stream()
            .map(word -> word.getObjectId("_id").toString())
            .collect(Collectors.toList());
    }
}
