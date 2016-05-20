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

    public Package getPackage(String package_id) throws TypeNotFoundException, PackageNotFoundException {
        return convertTo(this.findOneById(
            this.packages,
            package_id
        ));
    }

    private static Package convertTo(Document document) throws PackageNotFoundException {
        ArrayList<Document> wordsList = (ArrayList<Document>) document.get("words");
        ArrayList<String> words = new ArrayList<>();
        for (Object word_id : wordsList) {
            words.add((String) word_id);
        }
        return new Package(document.get("_id").toString(),
            document.getString("type_id"),
            document.getDouble("word_count").intValue(), words);
    }

    public List<Package> getUnactive(String userId, Map<String, ActivePackage> activePackages) throws ActivePackageNotFoundException, PackageNotFoundException {
        ArrayList<Package> unactivePackages = new ArrayList<>();
        for (Document document : this.packages.find()) {
            Package pack = convertTo(document);
            if (!activePackages.containsKey(pack.getId())) {
                unactivePackages.add(pack);
            }
        }
        return Collections.unmodifiableList(unactivePackages);
    }

}
