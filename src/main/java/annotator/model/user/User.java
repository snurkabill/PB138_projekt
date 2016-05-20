package annotator.model.user;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

public class User {

    private String id;
    private String email;
    private String passwordHash;

    public User(String email, String passwordHash) {
        this.id = null;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User(String email, String passwordHash, String id) throws UserNotFoundException {
        if (id == null)
            throw new UserNotFoundException("User : id not found");
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User(MongoDatabase database, String id) {
        this.id = id;
        BasicDBObject idObject = new BasicDBObject();
        idObject.put("_id", id);
        Document user = database.getCollection("users").find(idObject).first();
        this.passwordHash = user.getString("passwordHash");
        this.email = user.getString("email");
    }

    public User(String email, MongoDatabase database) {
        Document user = database.getCollection("users").find(Filters.eq("email", email)).first();
        this.passwordHash = user.getString("passwordHash");
        this.email = user.getString("email");
        this.id = user.get("_id").toString();
    }

    public String getEmail() {
        return this.email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }


    public String getId() {
        return this.id;
    }

    public boolean updateEmail(MongoDatabase database, String email) {
        UpdateResult result = database.getCollection("users").updateOne(
            new BasicDBObject("_id", new ObjectId(this.id)),
            new BasicDBObject("$set", new BasicDBObject("email", email)));
        System.out.println(database.getCollection("users").find(new BasicDBObject("_id", new ObjectId(this.id))).first().getString("email"));
        this.email = email;
        return result.wasAcknowledged();
    }
}
