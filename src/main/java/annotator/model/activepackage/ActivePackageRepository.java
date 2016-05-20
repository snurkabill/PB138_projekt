package annotator.model.activepackage;

import annotator.model.AbstractRepository;
import annotator.model.pack.Package;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ActivePackageRepository extends AbstractRepository {

    private MongoCollection<Document> activePackages;

    public ActivePackageRepository(MongoDatabase database) {
        super(database);
        this.activePackages = database.getCollection("activePackages");
    }

    public ActivePackage getActivePackage(String activePackae_id) throws ActivePackageNotFoundException {
        return convertTo(this.activePackages.find(
            new BasicDBObject("_id", new ObjectId(activePackae_id))).first());
    }

    public MongoCursor<Document> getActivePackagesIterator(String user_id) {
        return this.activePackages.find(Filters.eq("user_id", user_id)).iterator();
    }

    private static ActivePackage convertTo(Document document) throws ActivePackageNotFoundException {
        return new ActivePackage(document.get("_id").toString(), document.getString("user_id"),
            document.getString("package_id"), document.getInteger("progress"));
    }

    public Map<String, ActivePackage> getMapOfActivePackages(String user_id) throws ActivePackageNotFoundException {
        MongoCursor<Document> activePackages = this.getActivePackagesIterator(user_id);
        Map<String, ActivePackage> mapOfActivePackages = new HashMap<>();
        while (activePackages.hasNext()) {
            ActivePackage add = ActivePackageRepository.convertTo(activePackages.next());
            mapOfActivePackages.put(add.getPackageId(), add);
        }
        return Collections.unmodifiableMap(mapOfActivePackages);
    }

    public ActivePackage makeNew(Package pack, String userId) throws ActivePackageNotFoundException {
        Document newPack = new Document("_id", new ObjectId())
            .append("user_id", userId)
            .append("package_id", pack.getId())
            .append("progress", 0);
        this.activePackages.insertOne(newPack);
        return convertTo(newPack);
    }
}
