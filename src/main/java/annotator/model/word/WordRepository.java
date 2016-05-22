package annotator.model.word;

import annotator.model.AbstractRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class WordRepository extends AbstractRepository {

    private MongoCollection<Document> words;

    public WordRepository(MongoDatabase database) {
        this.words = database.getCollection("words");
    }

    public Word getWord(String wordId) throws WordNotFoundException {
        Document wordDocument = this.findOneById(
            this.words,
            wordId
        );

        if (wordDocument == null) {
            throw new WordNotFoundException(wordId);
        }

        return new Word(wordDocument);
    }
}
