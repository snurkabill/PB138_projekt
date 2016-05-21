package annotator.model.pack;

import annotator.model.AbstractRepository;
import annotator.model.activepackage.ActivePackage;
import annotator.model.activepackage.ActivePackageNotFoundException;
import annotator.model.type.TypeNotFoundException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PackageRepository extends AbstractRepository {

    private MongoCollection<Document> packages;

    public PackageRepository(MongoDatabase database) {
        this.packages = database.getCollection("packages");
    }

    public Package getPackage(String packageId) throws TypeNotFoundException, PackageNotFoundException {
        Document packageDocument = this.findOneById(
            this.packages,
            packageId
        );

        if (packageDocument == null) {
            throw new PackageNotFoundException(packageId);
        }

        return new Package(packageDocument);
    }

    public List<Package> getUnactive(String userId, Map<String, ActivePackage> activePackages) throws ActivePackageNotFoundException, PackageNotFoundException {
        ArrayList<Package> unactivePackages = new ArrayList<>();
        for (Document document : this.packages.find()) {
            Package pack = new Package(document);
            if (!activePackages.containsKey(pack.getId())) {
                unactivePackages.add(pack);
            }
        }
        return Collections.unmodifiableList(unactivePackages);
    }

}
