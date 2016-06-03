package annotator.model.user;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

public class UserCreator {

    public static final int PASSWORD_COST = 14;

    private final MongoCollection<Document> users;

    public UserCreator(MongoDatabase database) {
        this.users = database.getCollection("users");
    }

    public User create(String email, String password) throws UserCreateConflictException {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(UserCreator.PASSWORD_COST));

        try {
            Document userDocument = new Document()
                .append("email", email)
                .append("password_hash", passwordHash);

            this.users.insertOne(userDocument);
            return new User(userDocument);

        } catch (MongoWriteException e) {
            throw new UserCreateConflictException(email);
        }
    }
}
