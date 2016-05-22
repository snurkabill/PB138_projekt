package annotator.model.activepackage;

public class ActivePackageNotFoundException extends Exception {

    public ActivePackageNotFoundException(String activePackageId) {
        super(String.format(
            "Active package %s not found.",
            activePackageId
        ));
    }
}
