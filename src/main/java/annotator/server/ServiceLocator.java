package annotator.server;

import annotator.model.activepackage.ActivePackageCreator;
import annotator.model.activepackage.ActivePackageProgressKeeper;
import annotator.model.activepackage.ActivePackageRepository;
import annotator.model.pack.PackageCreator;
import annotator.model.pack.PackageRepository;
import annotator.model.type.TypeCreator;
import annotator.model.type.TypeRepository;
import annotator.model.user.UserCreator;
import annotator.model.user.UserRepository;
import annotator.model.vote.VoteRepository;
import annotator.model.vote.Voter;
import annotator.model.word.WordCreator;
import annotator.model.word.WordRepository;
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
        return (UserRepository) this.getService(
            "user_repository",
            () -> new UserRepository(this.database)
        );
    }

    public ActivePackageRepository getActivePackageRepository() {
        return (ActivePackageRepository) this.getService(
            "active_package_repository",
            () -> new ActivePackageRepository(this.database)
        );
    }

    public PackageRepository getPackageRepository() {
        return (PackageRepository) this.getService(
            "package_repository",
            () -> new PackageRepository(this.database)
        );
    }

    public WordRepository getWordRepository() {
        return (WordRepository) this.getService(
            "word_repository",
            () -> new WordRepository(this.database)
        );
    }

    public VoteRepository getVoteRepository() {
        return (VoteRepository) this.getService(
            "vote_repository",
            () -> new VoteRepository(this.database)
        );
    }

    public TypeRepository getTypeRepository() {
        return (TypeRepository) this.getService(
            "type_repository",
            () -> new TypeRepository(this.database)
        );
    }

    public UserCreator getUserCreator() {
        return (UserCreator) this.getService(
            "user_creator",
            () -> new UserCreator(this.database)
        );
    }

    public PackageCreator getPackageCreator() {
        return (PackageCreator) this.getService(
            "package_creator",
            () -> new PackageCreator(this.database)
        );
    }

    public WordCreator getWordCreator() {
        return (WordCreator) this.getService(
            "word_creator",
            () -> new WordCreator(this.database)
        );
    }

    public TypeCreator getTypeCreator() {
        return (TypeCreator) this.getService(
            "type_creator",
            () -> new TypeCreator(this.database)
        );
    }

    public Voter getVoter() {
        return (Voter) this.getService(
            "voter",
            () -> new Voter(this.database)
        );
    }

    public ActivePackageCreator getActivePackageCreator() {
        return (ActivePackageCreator) this.getService(
            "active_package_creator",
            () -> new ActivePackageCreator(this.database, this.getActivePackageRepository())
        );
    }

    public ActivePackageProgressKeeper getActivePackageProgressKeeper() {
        return (ActivePackageProgressKeeper) this.getService(
            "active_package_progress_keeper",
            () -> new ActivePackageProgressKeeper(this.database)
        );
    }
}
