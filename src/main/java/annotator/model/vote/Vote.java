package annotator.model.vote;

public class Vote {

    private final String userId;
    private final String wordId;
    private final String typeId;
    private final Boolean belongsToType;
    private final Long duration;

    public Vote(String userId, String wordId, String typeId, Boolean belongsToType, Long duration) {
        this.userId = userId;
        this.wordId = wordId;
        this.typeId = typeId;
        this.belongsToType = belongsToType;
        this.duration = duration;
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

    public Boolean getBelongsToType() {
        return belongsToType;
    }

    public Long getDuration() {
        return duration;
    }
}
