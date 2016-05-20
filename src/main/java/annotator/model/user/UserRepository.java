package annotator.model.user;

import annotator.model.AbstractRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

public class UserRepository extends AbstractRepository {

    private MongoCollection<Document> users;

    public UserRepository(MongoDatabase database) {
        this.users = database.getCollection("users");
    }

    public User getOneByEmail(String email) throws UserNotFoundException {
        return convertTo(this.users.find(Filters.eq("email", email)).first());
    }

    private static User convertTo(Document document) throws UserNotFoundException {
        return new User(document.getString("email"), document.getString("passwordHash"), document.get("_id").toString());
    }

    public User createUser(String email, String password) throws UserNotFoundException {
        Document newUser = new Document("_id", new ObjectId())
            .append("email", email)
            .append("passwordHash", password);
        this.users.insertOne(newUser);
        return convertTo(newUser);
    }
}
