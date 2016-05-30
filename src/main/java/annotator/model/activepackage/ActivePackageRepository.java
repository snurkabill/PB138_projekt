package annotator.model.activepackage;

import annotator.model.AbstractRepository;
import annotator.model.pack.Package;
import annotator.model.pack.PackageNotFoundException;
import annotator.model.pack.PackageRepository;
import annotator.model.type.TypeNotFoundException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

public class ActivePackageRepository extends AbstractRepository {

    private MongoCollection<Document> activePackages;

    public ActivePackageRepository(MongoDatabase database) {
        this.activePackages = database.getCollection("activePackages");
    }

    public ActivePackage getActivePackage(String packageId, String userId) throws ActivePackageNotFoundException {
        Document activePackageDocument = this.activePackages.find(Filters.and(
            Filters.eq("package_id", packageId),
            Filters.eq("user_id", userId)
        )).first();

        if (activePackageDocument == null) {
            throw new ActivePackageNotFoundException(packageId, userId);
        }

        return new ActivePackage(activePackageDocument);
    }

    public void update(ActivePackage activePackage){
        this.activePackages.updateOne(new BasicDBObject("_id", new ObjectId(activePackage.getId())),
        new BasicDBObject("$set", new BasicDBObject("progress", activePackage.getProgress())));
    }
}
