package annotator.model.word;

import annotator.model.AbstractRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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

    public List<Word> getWordByPackageId(String packageId) {
        MongoCursor<Document> cursor = words.find(
            Filters.eq("package_id", packageId)
        ).iterator();
        List<Word> words = new ArrayList<>();
        while (cursor.hasNext()) {
            words.add(new Word(cursor.next()));
        }
        cursor.close();
        return words;
    }
}
