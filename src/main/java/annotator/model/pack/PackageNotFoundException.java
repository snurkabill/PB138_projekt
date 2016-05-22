package annotator.model.pack;

public class PackageNotFoundException extends Exception {

    public PackageNotFoundException(String packageId) {
        super(String.format(
            "Package %s not found.",
            packageId
        ));
    }
}
