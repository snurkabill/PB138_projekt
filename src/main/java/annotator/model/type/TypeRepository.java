package annotator.model.type;

import annotator.model.AbstractRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    public ArrayList<Type> getTypesList() {
        ArrayList<Type> types = new ArrayList<>();

        for (Document document: this.types.find()) {
            types.add(new Type(document));
        }

        return types;
    }
}

