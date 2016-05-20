package annotator.model.vote;

import annotator.model.AbstractRepository;
import annotator.model.word.Word;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

public class VoteRepository extends AbstractRepository {

    private MongoCollection<Document> votes;

    public VoteRepository(MongoDatabase database) {
        this.votes = database.getCollection("votes");
    }

    public void addVote(String userId, Word word, Boolean voteResult, Integer duration) {
        Document newVote = new Document("_id", new ObjectId())
            .append("user_id", userId)
            .append("word_id", word.getId())
            .append("type_id", word.getTypeId())
            .append("belongs_to_type", word.belongsToType())
            .append("vote_belongs_to_type", voteResult)
            .append("duration", duration);
        this.votes.insertOne(newVote);
    }

}
