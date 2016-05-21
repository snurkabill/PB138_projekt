package annotator.server;

import annotator.model.user.UserRepository;
import com.mongodb.client.MongoDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ServiceLocator {

    private final MongoDatabase database;
    private Map<String, Object> services;

    public ServiceLocator(MongoDatabase database) {
        this.database = database;
        this.services = new HashMap<>();
    }

    private Object getService(String name, Supplier factory) {
        if (this.services.get(name) == null) {
            this.services.put(name, factory.get());
        }
        return this.services.get(name);
    }

    public UserRepository getUserRepository() {
        return (UserRepository) this.getService("user_repository",
            () -> new UserRepository(this.database)
        );
    }

}
