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
import org.bson.Document;
import org.bson.types.ObjectId;

public class ActivePackageRepository extends AbstractRepository {

    private MongoCollection<Document> activePackages;

    public ActivePackageRepository(MongoDatabase database) {
        this.activePackages = database.getCollection("activePackages");
    }

    public ActivePackage getActivePackage(String activePackageId) throws ActivePackageNotFoundException {
        Document activePackageDocument = this.findOneById(
            this.activePackages,
            activePackageId
        );

        if (activePackageDocument == null) {
            throw new ActivePackageNotFoundException(activePackageId);
        }

        return new ActivePackage(activePackageDocument);
    }

    public ActivePackage makeNew(Package pack, String userId) {
        Document newPack = new Document()
            .append("user_id", userId)
            .append("package_id", pack.getId())
            .append("progress", 0);
        this.activePackages.insertOne(newPack);
        return new ActivePackage(newPack);
    }

    public ActivePackage getOrMakeNew(String packageId, String userId, PackageRepository packageRepository) throws PackageNotFoundException, TypeNotFoundException {
        ActivePackage activePackage;
        try {
            activePackage = this.getActivePackage(packageId);
        } catch (ActivePackageNotFoundException e) {
            System.out.println("There is no active package od this type, creating new...\n");
            activePackage = null;
        }
        return activePackage == null
            ? this.makeNew(packageRepository.getPackage(packageId), userId)
            : activePackage;
    }

    public void update(ActivePackage activePackage){
        this.activePackages.updateOne(new BasicDBObject("_id", new ObjectId(activePackage.getId())),
        new BasicDBObject("$set", new BasicDBObject("progress", activePackage.getProgress())));
    }
}
