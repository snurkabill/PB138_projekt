package annotator.model.activepackage;

import annotator.model.AbstractRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

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
}
