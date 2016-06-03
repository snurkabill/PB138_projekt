package annotator.model.activepackage;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

public class ActivePackageProgressKeeper {

    private MongoCollection<Document> activePackages;

    public ActivePackageProgressKeeper(MongoDatabase database) {
        this.activePackages = database.getCollection("activePackages");
    }

    public void update(ActivePackage activePackage) {
        this.activePackages.updateOne(
            new Document("_id", new ObjectId(activePackage.getId())),
            Updates.set("progress", activePackage.getProgress())
        );
    }
}
