package annotator.model;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public abstract class AbstractRepository {

    protected final MongoDatabase database;

    public AbstractRepository(MongoDatabase database) {
        this.database = database;
    }

    protected boolean typeExists(String typeId) {
        return database.getCollection("types").find(Filters.eq("_id", typeId)).first() != null;
    }
}
