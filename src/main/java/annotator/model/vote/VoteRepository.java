package annotator.model.vote;

import annotator.model.AbstractRepository;
import annotator.model.type.TypeNotFoundException;
import annotator.model.user.UserNotFoundException;
import annotator.model.word.WordNotFoundException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

public class VoteRepository extends AbstractRepository {

    private MongoCollection<Document> votes;

    public VoteRepository(MongoDatabase database) {
        super(database);
        votes = database.getCollection("votes");
    }

    public Vote getVote(String vote_id) throws VoteNotFoundException, TypeNotFoundException, UserNotFoundException, WordNotFoundException {
        return convertTo(votes.find(new BasicDBObject("_id", new ObjectId(vote_id))).first());
    }

    public static Vote convertTo(Document document) throws VoteNotFoundException {
        return new Vote(document.get("_id").toString(),
                document.getString("user_id"), document.getString("word_id"),
                document.getString("type_id"), document.getBoolean("belongs_to_type"),
                document.getInteger("duration"));
    }

}
