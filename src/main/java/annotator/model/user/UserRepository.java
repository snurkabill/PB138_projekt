package annotator.model.user;

import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class UserRepository {

    private MongoDatabase database;

    public UserRepository(MongoDatabase database) {
        this.database = database;
    }

    public User getOneByEmail(String email) throws UserNotFoundException {
        Document document = this.database.getCollection("users").find(Filters.eq("email", email)).first();
        if (document == null) {
            throw new UserNotFoundException(email);
        }
        return new User(
            document.getString("email"),
            document.getString("passwordHash")
        );
    }

}
