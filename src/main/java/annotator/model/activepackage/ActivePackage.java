package annotator.model.activepackage;


public class ActivePackage {

    private final String userId;
    private final String packageId;
    private final Integer progress;

    public ActivePackage(String userId, String packageId, Integer progress) {
        this.userId = userId;

        this.packageId = packageId;
        this.progress = progress;
    }

    public String getUserId() {
        return userId;
    }

    public String getPackageId() {
        return packageId;
    }

    public Integer getProgress() {
        return progress;
    }

}
