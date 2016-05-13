package annotator.model.type;

import annotator.model.word.WordNotFoundException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

public class TypeRepository {

    private MongoCollection<Document> types;

    public TypeRepository(MongoDatabase database) {
        this.types = database.getCollection("types");
    }


    public Type getType(String type_id) throws TypeNotFoundException {
        return convertTo(types.find(new BasicDBObject("_id", new ObjectId(type_id))).first());
    }

    public static Type convertTo(Document document) throws TypeNotFoundException {
        return new Type(document.get("_id").toString(), document.getString("type"));
    }
}



