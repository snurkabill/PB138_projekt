package annotator.model.word;

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
        String wordTypeId = document.getString("type_id");
        Document document2 = database.getCollection("types").find(Filters.eq("_id", wordTypeId)).first();

        if (document2 == null) {
            throw new TypeNotFoundException("Word: " + word + " has no-existing typeId: " + wordTypeId);
        }

        return new Word(
                wordTypeId,
                document.getString("word"),
                document.getBoolean("isNoise")
        );
    }
}
