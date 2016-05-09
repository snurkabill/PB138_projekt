package annotator.model.user;

import annotator.model.AbstractRepository;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class UserRepository extends AbstractRepository {

    public UserRepository(MongoDatabase database) {
        super(database);
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
