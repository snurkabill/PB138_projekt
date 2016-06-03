package annotator.model.type;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class TypeCreator {

    private final MongoCollection<Document> types;

    public TypeCreator(MongoDatabase database) {
        this.types = database.getCollection("types");
    }

    public Type create(String type) throws TypeCreateConflictException {
        try {
            Document typeDocument = new Document()
                .append("type", type);

            this.types.insertOne(typeDocument);
            return new Type(typeDocument);

        } catch (MongoWriteException e) {
            throw new TypeCreateConflictException(type);
        }
    }
}
