package annotator.model.activepackage;

import annotator.model.AbstractRepository;
import annotator.model.pack.PackageNotFoundException;
import annotator.model.user.UserNotFoundException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class ActivePackageRepository extends AbstractRepository {

    public ActivePackageRepository(MongoDatabase database) {
        super(database);
    }

    public ActivePackage getActivePackage(String activePackage) throws ActivePackageNotFoundException, UserNotFoundException, PackageNotFoundException {
        Document document = database
                .getCollection("activePackages")
                .find(Filters.eq("activePackage", activePackage))
                .first();
        if(document == null) {
            throw new ActivePackageNotFoundException(activePackage);
        }
        String userId = document.getString("user_id");
        String packageId = document.getString("package_id");
        if(!typeIdExists(userId)) {
            throw new UserNotFoundException("ActivePackage: " + activePackage + " has no-existing userId: " + userId);
        }
        if(!packageIdExists(packageId)) {
            throw new PackageNotFoundException("ActivePackage: " + activePackage + " has no-existing packageId: " + packageId);
        }

        return new ActivePackage(
                userId,
                packageId,
                document.getInteger("progress")
        );

    }
}
