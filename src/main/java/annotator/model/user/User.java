package annotator.model.user;

import org.bson.Document;

public class User {

    private String id;
    private String email;
    private String passwordHash;

    public User(String id, String email, String passwordHash) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User(Document document) {
        this(
            document.getObjectId("_id").toString(),
            document.getString("email"),
            document.getString("password_hash")
        );
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
