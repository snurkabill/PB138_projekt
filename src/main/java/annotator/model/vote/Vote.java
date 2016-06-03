package annotator.model.vote;

import org.bson.Document;

public class Vote {

    private final String id;
    private final String userId;
    private final String wordId;
    private final String typeId;
    private final String packageId;
    private final Boolean belongsToType;
    private final Boolean voteBelongsToType;
    private final Integer duration;

    public Vote(
        String id,
        String userId,
        String wordId,
        String typeId,
        String packageId,
        Boolean belongsToType,
        Boolean voteBelongsToType,
        Integer duration
    ) {
        this.id = id;
        this.userId = userId;
        this.wordId = wordId;
        this.typeId = typeId;
        this.packageId = packageId;
        this.belongsToType = belongsToType;
        this.voteBelongsToType = voteBelongsToType;
        this.duration = duration;
    }

    public Vote(Document document) {
        this(
            document.getObjectId("_id").toString(),
            document.getString("user_id"),
            document.getString("word_id"),
            document.getString("type_id"),
            document.getString("package_id"),
            document.getBoolean("belongs_to_type"),
            document.getBoolean("vote_belongs_to_type"),
            document.getInteger("duration")
        );
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

    public String getPackageId() {
        return this.packageId;
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
