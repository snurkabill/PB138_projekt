package annotator.model.pack;

import annotator.model.AbstractRepository;
import annotator.model.type.Type;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.*;

public class PackageRepository extends AbstractRepository {

    private MongoCollection<Document> packages;

    public PackageRepository(MongoDatabase database) {
        this.packages = database.getCollection("packages");
    }

    public Package getPackage(String packageId) throws PackageNotFoundException {
        Document packageDocument = this.findOneById(
            this.packages,
            packageId
        );

        if (packageDocument == null) {
            throw new PackageNotFoundException(packageId);
        }

        return new Package(packageDocument);
    }

    public Map<String, Package> getPackagesMap() {
        HashMap<String, Package> packages = new HashMap<>();

        Package pack;
        for (Document document : this.packages.find()) {
            pack = new Package(document);
            packages.put(pack.getId(), pack);
        }

        return packages;
    }

    public List<Package> getPackagesByType(Type type) {
        ArrayList<Package> packages = new ArrayList<>();

        for (Document document : this.packages.find(Filters.eq("type_id", type.getId()))) {
            packages.add(new Package(document));
        }

        return packages;
    }
}
