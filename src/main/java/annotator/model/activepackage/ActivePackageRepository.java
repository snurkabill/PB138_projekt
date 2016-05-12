package annotator.model.activepackage;

import annotator.model.AbstractRepository;
import annotator.model.pack.PackageNotFoundException;
import annotator.model.user.UserNotFoundException;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class ActivePackageRepository extends AbstractRepository {

    private MongoCursor iterable;

    public ActivePackageRepository(MongoDatabase database, String userId) {
        super(database);
        iterable = database.getCollection("activePackages").find(Filters.eq("user_id", userId)).iterator();
    }

    public Document getNext() {
        if (iterable.hasNext()) {
            return (Document)iterable.next();
        }
        return null;
    }
}
