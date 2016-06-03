package annotator.model.vote;

import annotator.model.word.Word;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Voter {

    private MongoCollection<Document> votes;

    public Voter(MongoDatabase database) {
        this.votes = database.getCollection("votes");
    }

    public void vote(String userId, Word word, Boolean voteResult, Integer duration) {
        Document newVote = new Document()
            .append("user_id", userId)
            .append("word_id", word.getId())
            .append("type_id", word.getTypeId())
            .append("belongs_to_type", word.belongsToType())
            .append("vote_belongs_to_type", voteResult)
            .append("duration", duration);

        this.votes.insertOne(newVote);
    }
}
