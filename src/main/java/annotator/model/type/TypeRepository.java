package annotator.model.type;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class TypeRepository {

    private final MongoDatabase database;

    public TypeRepository(MongoDatabase database) {
        this.database = database;
    }

    public Type getType(String type) throws TypeNotFoundException {
        Document document = this.database.getCollection("types").find(Filters.eq("type", type)).first();
        if (document == null) {
            throw new TypeNotFoundException(type);
        }
        return new Type(
                document.getString("type")
        );
    }

}



