package annotator.model.activepackage;

import org.bson.Document;

public class ActivePackage {

    private final String id;
    private final String userId;
    private final String packageId;
    private Integer progress;

    public ActivePackage(
        String id,
        String userId,
        String packageId,
        Integer progress
    ) {
        this.id = id;
        this.userId = userId;
        this.packageId = packageId;
        this.progress = progress;
    }

    public ActivePackage(Document document) {
        this(
            document.getObjectId("_id").toString(),
            document.getString("user_id"),
            document.getString("package_id"),
            document.getInteger("progress")
        );
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public ActivePackage increaseProgress() {
        this.progress++;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        ActivePackage that = (ActivePackage) o;

        return this.id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
