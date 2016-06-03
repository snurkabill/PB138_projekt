package annotator.model.word;


import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class WordCreator {

    private MongoCollection<Document> words;

    public WordCreator(MongoDatabase database) {
        this.words = database.getCollection("words");
    }

    public Word create(String typeId, String word, Boolean belongsToType) throws WordCreateConflictException {
        try {
            Document wordDocument = new Document()
                .append("type_id", typeId)
                .append("word", word)
                .append("belongs_to_type", belongsToType);

            this.words.insertOne(wordDocument);
            return new Word(wordDocument);

        } catch (MongoWriteException e) {
            throw new WordCreateConflictException();
        }
    }
}
