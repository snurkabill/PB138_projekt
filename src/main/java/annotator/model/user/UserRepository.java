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
        Document user = this.database.getCollection("users").find(Filters.eq("email", email)).first();
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        return new User(
            user.getString("email"),
            user.getString("passwordHash")
        );
    }

}
