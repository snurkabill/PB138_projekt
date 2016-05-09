package annotator.model.word;


import annotator.model.type.Type;

public class Word {

    private final Type type;
    private final String word;
    private final boolean isNoise;

    public Word(Type type, String word, boolean isNoise) {
        this.type = type;
        this.word = word;
        this.isNoise = isNoise;
    }
}
