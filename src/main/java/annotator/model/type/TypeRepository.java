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


    public Type getType(String type_id) throws TypeNotFoundException {
        return convertTo(this.findOneById(
            this.types,
            type_id
        ));
    }

    public static Type convertTo(Document document) throws TypeNotFoundException {
        return new Type(document.get("_id").toString(), document.getString("type"));
    }
}



