package annotator.model.type;

public class TypeNotFoundException extends Exception {

    public TypeNotFoundException(String typeId) {
        super(String.format(
            "Type %s not found.",
            typeId
        ));
    }
}
