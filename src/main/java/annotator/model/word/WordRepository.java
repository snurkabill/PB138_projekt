package annotator.model.word;

import annotator.model.AbstractRepository;
import annotator.model.type.TypeNotFoundException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class WordRepository extends AbstractRepository {

    public WordRepository(MongoDatabase database) {
        super(database);
    }

    public Word getWord(String word) throws TypeNotFoundException, WordNotFoundException {
        Document document = database.getCollection("words").find(Filters.eq("word", word)).first();
        if (document == null) {
            throw new WordNotFoundException(word);
        }
        String typeId = document.getString("type_id");
        if(!typeIdExists(typeId)) {
            throw new TypeNotFoundException("Word: " + word + " has no-existing typeId: " + typeId);
        }
        return new Word(
                typeId,
                document.getString("word"),
                document.getBoolean("isNoise")
        );
    }
}
