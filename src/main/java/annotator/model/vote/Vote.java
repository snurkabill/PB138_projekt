package annotator.model.vote;

public class Vote {

    private final String id;
    private final String userId;
    private final String wordId;
    private final String typeId;
    private final Boolean belongsToType;
    private final Integer duration;

    public Vote(String id, String userId, String wordId, String typeId, Boolean belongsToType, Integer duration) throws VoteNotFoundException {
        if (id == null)
            throw new VoteNotFoundException("Vote: id not found");
        this.id = id;
        this.userId = userId;
        this.wordId = wordId;
        this.typeId = typeId;
        this.belongsToType = belongsToType;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getWordId() {
        return wordId;
    }

    public String getTypeId() {
        return typeId;
    }

    public Boolean isBelongsToType() {
        return belongsToType;
    }

    public Integer getDuration() {
        return duration;
    }
}
