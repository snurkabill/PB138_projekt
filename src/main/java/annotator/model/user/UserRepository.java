package annotator.model.user;

import annotator.model.AbstractRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class UserRepository extends AbstractRepository {

    private MongoCollection<Document> users;

    public UserRepository(MongoDatabase database) {
        this.users = database.getCollection("users");
    }

    public User getOneByEmail(String email) throws UserNotFoundException {
        Document userDocument = this.users.find(Filters.eq(
            "email",
            email
        )).first();

        if (userDocument == null) {
            throw new UserNotFoundException("User not found: " + email);
        }

        return new User(userDocument);
    }
}
