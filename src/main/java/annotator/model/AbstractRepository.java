package annotator.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

public abstract class AbstractRepository {

    protected Document findOneById(MongoCollection<Document> collection, String id) {
        return collection.find(
            Filters.eq("_id", new ObjectId(id))
        ).first();
    }
}
