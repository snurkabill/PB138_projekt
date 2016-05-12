package annotator.model.user;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;

public class User {

    private String id;
    private String email;
    private String passwordHash;


    public User(String email, String passwordHash) {
        id = "1";
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User(MongoDatabase database,String id) {
        this.id = id;
        BasicDBObject idObject = new BasicDBObject();
        idObject.put("_id", id);
        Document user = database.getCollection("users").find(idObject).first();
        passwordHash = user.getString("passwordHash");
        email = user.getString("email");
    }

    public User(String email,MongoDatabase database) {
        Document user = database.getCollection("users").find(Filters.eq("email", email)).first();
        passwordHash = user.getString("passwordHash");
        this.email = user.getString("email");
        this.id = user.get("_id").toString();
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }


    public String getId() {
        return id;
    }

    public boolean updateEmail(MongoDatabase database, String email) {
        UpdateResult result = database.getCollection("users").updateOne(
                new BasicDBObject("_id", new ObjectId(id)),
                new BasicDBObject("$set", new BasicDBObject("email", email)));
        System.out.println(database.getCollection("users").find(new BasicDBObject("_id", new ObjectId(id))).first().getString("email"));
        this.email = email;
        return result.wasAcknowledged();
    }
}
