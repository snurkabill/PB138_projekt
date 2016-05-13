package annotator.model;

import com.mongodb.client.MongoDatabase;

public abstract class AbstractRepository {

    protected final MongoDatabase database;

    public AbstractRepository(MongoDatabase database) {
        this.database = database;
    }

}
