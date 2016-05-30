package annotator.model.activepackage;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ActivePackageCreator {

    private MongoCollection<Document> activePackages;
    private ActivePackageRepository activePackageRepository;

    public ActivePackageCreator(MongoDatabase database, ActivePackageRepository activePackageRepository) {
        this.activePackages = database.getCollection("activePackages");
        this.activePackageRepository = activePackageRepository;
    }

    public ActivePackage create(String packageId, String userId) {
        Document newPack = new Document()
            .append("user_id", userId)
            .append("package_id", packageId)
            .append("progress", 0);
        this.activePackages.insertOne(newPack);
        return new ActivePackage(newPack);
    }

    public ActivePackage createIfDoesNotExist(String packageId, String userId) {
        try {
            return this.activePackageRepository.getActivePackage(
                packageId,
                userId
            );
        } catch (ActivePackageNotFoundException e) {
            return this.create(packageId, userId);
        }
    }
}
