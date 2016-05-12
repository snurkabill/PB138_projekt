package annotator.model.activepackage;


import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;


public class ActivePackage {

    private final String id;
    private final String userId;
    private final String packageId;
    private Integer progress;

    public ActivePackage(String id, String userId, String packageId, Integer progress) {
        this.id = id;
        this.userId = userId;
        this.packageId = packageId;
        this.progress = progress;
    }

    public ActivePackage(MongoDatabase database, String id) throws ActivePackageNotFoundException {
        Document document = database
                .getCollection("activePackages")
                .find(Filters.eq("_id", id))
                .first();
        if(document == null) {
            throw new ActivePackageNotFoundException(id);
        }
        this.id = id;
        userId = document.getString("user_id");
        packageId = document.getString("package_id");
        progress = document.getInteger("progress");

    }

    public String getId() { return id; }

    public String getUserId() {
        return userId;
    }

    public String getPackageId() {
        return packageId;
    }

    public Integer getProgress() {
        return progress;
    }


    public boolean updateProgress(MongoDatabase database, Integer progress) {
        UpdateResult result = database.getCollection("activePackages").updateOne(
                new BasicDBObject("_id", new ObjectId(id)),
                new BasicDBObject("$set", new BasicDBObject("progress", progress)));
        this.progress = progress;
        return result.wasAcknowledged();
    }

}
