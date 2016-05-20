package annotator.model.word;

import annotator.model.AbstractRepository;
import annotator.model.type.TypeNotFoundException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

public class WordRepository extends AbstractRepository {

    private MongoCollection<Document> words;

    public WordRepository(MongoDatabase database) {
        super(database);
        this.words = database.getCollection("words");
    }

    public Word getWord(String word_id) throws TypeNotFoundException, WordNotFoundException {
        return convertTo(
            this.words.find(Filters.eq("_id", new ObjectId(word_id))).first()
        );
    }

    private static Word convertTo(Document document) throws WordNotFoundException {
        if (document.containsValue("belongs_to_type")) {
            return new Word(document.get("_id").toString(), document.getString("type_id"),
                document.getString("word"), (Boolean) document.get("belongs_to_type"));
        } else {
            return new Word(document.get("_id").toString(), document.getString("type_id"),
                document.getString("word"));
        }

    }
}
