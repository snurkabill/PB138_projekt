package annotator.model;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public abstract class AbstractRepository {

    protected final MongoDatabase database;

    public AbstractRepository(MongoDatabase database) {
        this.database = database;
    }

    protected boolean typeIdExists(String typeId) {
        return database.getCollection("types").find(Filters.eq("_id", typeId)).first() != null;
    }

    protected boolean wordIdExists(String wordId) {
        return database.getCollection("words").find(Filters.eq("_id", wordId)).first() != null;
    }

    protected boolean userIdExists(String userid) {
        return database.getCollection("users").find(Filters.eq("_id", userId)).first() != null;
    }


}
