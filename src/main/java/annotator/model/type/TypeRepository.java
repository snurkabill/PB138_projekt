package annotator.model.type;

import annotator.model.AbstractRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;

public class TypeRepository extends AbstractRepository {

    private final MongoCollection<Document> types;

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

        for (Document document : this.types.find()) {
            types.add(new Type(document));
        }

        return types;
    }

    public Type findOneByType(String type) {
        Document typeDocument = this.types.find(Filters.eq("type", type)).first();

        if (typeDocument == null) {
            return null;
        }

        return new Type(typeDocument);
    }

    public void removeType(String typeId) {
        Document typeDocument = this.findOneById(
            this.types,
            typeId
        );

        this.types.deleteOne(typeDocument);
    }
}

