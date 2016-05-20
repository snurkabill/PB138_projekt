package annotator.model.type;

public class Type {

    private final String id;
    private final String type;

    public Type(String id, String type) throws TypeNotFoundException {
        if (id == null)
            throw new TypeNotFoundException("Word: id not found");
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
