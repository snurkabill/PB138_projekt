package annotator.model.type;

import annotator.model.AbstractRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class TypeRepository extends AbstractRepository {

    private MongoCollection<Document> types;

    public TypeRepository(MongoDatabase database) {
        this.types = database.getCollection("types");
    }

    public Type getType(String typeId) throws TypeNotFoundException {
        Document typeDocument = this.findOneById(
            this.types,
            typeId
        );

        if (typeDocument == null) {
            throw new TypeNotFoundException(typeId);
        }

        return new Type(typeDocument);
    }
}

