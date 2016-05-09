package annotator.model.word;

import annotator.model.type.Type;
import annotator.model.type.TypeNotFoundException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class WordRepository {

    private MongoDatabase database;

    public WordRepository(MongoDatabase database) {
        this.database = database;
    }

    public Word getWord(String word) throws TypeNotFoundException, WordNotFoundException {
        Document document = database.getCollection("words").find(Filters.eq("word", word)).first();
        if (document == null) {
            throw new WordNotFoundException(word);
        }
        String wordType = document.getString("type_id");
        Document document2 = database.getCollection("types").find(Filters.eq("_id", wordType)).first();

        if (document2 == null) {
            throw new TypeNotFoundException("Word: " + word + " has no-existing type: " + wordType);
        }

        return new Word(
                new Type(
                        document2.getString("type")),
                document.getString("word"),
                document.getBoolean("isNoise")
        );
    }
}
