package annotator.model.vote;

import annotator.model.AbstractRepository;
import annotator.model.type.TypeNotFoundException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class VoteRepository extends AbstractRepository {

    public VoteRepository(MongoDatabase database) {
        super(database);
    }

    public Vote getVote(String vote) throws VoteNotFoundException, TypeNotFoundException {
        Document document = database.getCollection("votes").find(Filters.eq("vote", vote)).first();
        if(document == null) {
            throw new VoteNotFoundException(vote);
        }

        String typeId = document.getString("type_id");
        if(!typeExists(typeId)) {
            throw new TypeNotFoundException("Vote: " + vote + " has no-existing typeId: " + typeId);
        }

        throw new UnsupportedOperationException("Not finished yet");


    }

}
