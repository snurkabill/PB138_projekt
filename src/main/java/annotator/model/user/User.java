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

    public User(String email, String passwordHash, String id) throws UserNotFoundException {
        if (id == null)
            throw new UserNotFoundException("User : id not found");
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
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
}
