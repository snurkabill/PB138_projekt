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

    public ActivePackage(String id, String userId, String packageId, Integer progress) throws ActivePackageNotFoundException {
        if (id == null)
            throw new ActivePackageNotFoundException("ActivePackage: id not found");
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
        if (document == null) {
            throw new ActivePackageNotFoundException(id);
        }
        this.id = id;
        this.userId = document.getString("user_id");
        this.packageId = document.getString("package_id");
        this.progress = document.getInteger("progress");

    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public Integer getProgress() {
        return this.progress;
    }


    public boolean increaseProgress(MongoDatabase database) {
        this.progress++;
        UpdateResult result = database.getCollection("activePackages").updateOne(
            new BasicDBObject("_id", new ObjectId(this.id)),
            new BasicDBObject("$set", new BasicDBObject("progress", this.progress)));
        return result.wasAcknowledged();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        ActivePackage that = (ActivePackage) o;

        return this.id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
