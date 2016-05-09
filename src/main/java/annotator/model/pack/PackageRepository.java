package annotator.model.pack;

import annotator.model.AbstractRepository;
import annotator.model.type.TypeNotFoundException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class PackageRepository extends AbstractRepository {

    public PackageRepository(MongoDatabase database) {
        super(database);
    }

    public Package getPackage(String pack) throws TypeNotFoundException, PackageNotFoundException {
        Document document = database.getCollection("packages").find(Filters.eq("package", pack)).first();


        if (document == null) {
            throw new PackageNotFoundException(pack);
        }

        String typeId = document.getString("type_id");
        if(!typeIdExists(typeId)) {
            throw new TypeNotFoundException("Package: " + pack + " has no-existing typeId: " + typeId);
        }

        List<String> wordList = new ArrayList<>();

        throw new UnsupportedOperationException("Not supported yet. Don't know how to get collection from JSON");
//        document.get("words");
//
//        return new Package(
//                document.getString("type_id"),
//                document.getInteger("wordCount"),
//                wordList
//        );
    }

}
