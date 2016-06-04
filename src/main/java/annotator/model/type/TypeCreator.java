package annotator.model.type;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class TypeCreator {

    private final MongoCollection<Document> types;

    public TypeCreator(MongoDatabase database) {
        this.types = database.getCollection("types");
    }

    public Type createIfDoesNotExist(String type) {
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
        options.upsert(true);
        options.returnDocument(ReturnDocument.AFTER);

        Document typeDocument = this.types.findOneAndUpdate(
            Filters.eq("type", type),
            Updates.set("type", type),
            options
        );

        return new Type(typeDocument);
    }
}
