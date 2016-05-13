package annotator.model.activepackage;

import annotator.model.AbstractRepository;
import annotator.model.pack.Package;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ActivePackageRepository extends AbstractRepository {

    private MongoCollection<Document> activePackages;

    public ActivePackageRepository(MongoDatabase database) {
        super(database);
        activePackages = database.getCollection("activePackages");
    }

    public ActivePackage getActivePackage(String activePackae_id) throws ActivePackageNotFoundException {
        return convertTo(activePackages.find(
                new BasicDBObject("_id", new ObjectId(activePackae_id))).first());
    }

    public MongoCursor<Document> getActivePackagesIterator() {
        return  activePackages.find().iterator();
    }

    MongoCursor<Document> getActivePackagesIterator(String user_id) {
        return  activePackages.find(Filters.eq("user_id", user_id)).iterator();
    }

    private static ActivePackage convertTo(Document document) throws ActivePackageNotFoundException {
        return new ActivePackage(document.get("_id").toString(), document.getString("user_id"),
                document.getString("package_id"), document.getInteger("progress"));
    }

    public Map<String, ActivePackage> getMapOfActivePackages(String user_id) throws ActivePackageNotFoundException {
        MongoCursor<Document> activePackages = this.getActivePackagesIterator(user_id);
        Map<String, ActivePackage> mapOfActivePackages = new TreeMap<>();
        while (activePackages.hasNext()) {
            ActivePackage add = ActivePackageRepository.convertTo(activePackages.next());
            mapOfActivePackages.put(add.getId(), add);
        }
        return Collections.unmodifiableMap(mapOfActivePackages);
    }

}
