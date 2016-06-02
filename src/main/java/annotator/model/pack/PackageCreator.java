package annotator.model.pack;


import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class PackageCreator {

    private MongoCollection<Document> packages;

    public PackageCreator(MongoDatabase database) { this.packages = database.getCollection("packages"); }

    public Package create(String typeId, String name, Integer wordCount, ArrayList<String> wordList) throws PackageCreateConflictException {
        try {
            Document packageDocument = new Document()
                    .append("type_id", typeId)
                    .append("name", name)
                    .append("word_count", wordCount)
                    .append("words", wordList);

            this.packages.insertOne(packageDocument);
            return new Package(packageDocument);

        } catch (MongoWriteException e) {
            throw new PackageCreateConflictException("Conflict with package " + name);
        }
    }
}
