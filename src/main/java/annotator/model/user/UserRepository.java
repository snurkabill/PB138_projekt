package annotator.model.user;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;

public class UserRepository {

    private MongoCollection<Document> users;

    public UserRepository(MongoDatabase database) {
        users = database.getCollection("users");
    }

    public User getOneByEmail(String email) throws UserNotFoundException {
        Document user = (Document)this.users.find(Filters.eq("email", email)).first();
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        return new User(
            user.getString("email"),
            user.getString("passwordHash")
        );
    }

    public User createUser(String email, String password) {
        Document newUser = new Document("_id", new ObjectId())
                .append("email", email)
                .append("passwordHash", password);
        users.insertOne(newUser);
        return new User(email,password, newUser.get("_id").toString());
    }
}
