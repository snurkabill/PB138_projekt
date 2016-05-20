package annotator.model.vote;

public class Vote {
    private final String id;
    private final String userId;
    private final String wordId;
    private final String typeId;
    private final Boolean belongsToType;
    private final Boolean voteBelongsToType;
    private final Integer duration;

    public Vote(String id, String userId, String wordId, String typeId, Boolean belongsToType, Boolean voteBelongsToType, Integer duration) throws VoteNotFoundException {
        if (id == null)
            throw new VoteNotFoundException("Vote: id not found");
        this.id = id;
        this.userId = userId;
        this.wordId = wordId;
        this.typeId = typeId;
        this.belongsToType = belongsToType;
        this.voteBelongsToType = voteBelongsToType;
        this.duration = duration;
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getWordId() {
        return this.wordId;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public Boolean getVoteBelongsToType() {
        return this.voteBelongsToType;
    }

    public Boolean getBelongsToType() {
        return this.belongsToType;
    }

    public Integer getDuration() {
        return this.duration;
    }
}
