package annotator.model.pack;

import annotator.model.AbstractRepository;
import annotator.model.type.TypeNotFoundException;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class PackageRepository extends AbstractRepository {

    private MongoCollection<Document> packages;

    public PackageRepository(MongoDatabase database) {
        super(database);
        packages = database.getCollection("packages");
    }

    public Package getPackage(String package_id) throws TypeNotFoundException, PackageNotFoundException {
       return convertTo(packages.find(
               new BasicDBObject("_id", new ObjectId(package_id))).first());
    }

    public MongoCursor<Document> getPackagesIterator() {
        return packages.find().iterator();
    }

    private static Package convertTo(Document document) throws PackageNotFoundException {
        BasicDBList wordsList = (BasicDBList) document.get("words");
        ArrayList<String> words = new ArrayList<>();
        for (Object word_id : wordsList) {
            words.add((String)word_id);
        }
        return new Package(document.get("_id").toString(),
                document.getString("type_id"),
                document.getInteger("word_count"),words);
    }


}
