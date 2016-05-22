package annotator.model.activepackage;

import annotator.model.AbstractRepository;
import annotator.model.pack.Package;
import annotator.model.user.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.*;

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
}
