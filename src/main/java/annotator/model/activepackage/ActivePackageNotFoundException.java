package annotator.model.activepackage;

public class ActivePackageNotFoundException extends Exception {

    public ActivePackageNotFoundException(String packageId, String userId) {
        super(String.format(
            "Active package not found for package id %s and user id %s.",
            packageId,
            userId
        ));
    }
}
