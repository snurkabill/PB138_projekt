package annotator.model.pack;

import annotator.model.type.Type;
import annotator.model.type.TypeCreator;
import annotator.model.word.WordCreator;
import au.com.bytecode.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageImporter {

    public final static Integer PACKAGE_SIZE = 1000;

    private final PackageCreator packageCreator;
    private final WordCreator wordCreator;
    private final TypeCreator typeCreator;

    public PackageImporter(
        PackageCreator packageCreator,
        WordCreator wordCreator,
        TypeCreator typeCreator
    ) {
        this.packageCreator = packageCreator;
        this.wordCreator = wordCreator;
        this.typeCreator = typeCreator;
    }

    public List<Package> importPackage(String typeName, String packageName, InputStream file) throws InvalidFileFormatException, IOException, PackageCreateConflictException {

        Map<String, Boolean> words = this.parseWords(file);
        Type type = this.typeCreator.createIfDoesNotExist(typeName);

        List<String> wordIds = this.wordCreator.createMany(type.getId(), words);
        if (wordIds.size() == 0) {
            throw new InvalidFileFormatException("Empty file.");
        }

        List<String> chunk;
        List<Package> packages = new ArrayList<>();
        Integer packageCounter = 0;
        while (wordIds.size() > 0) {
            Integer end = Math.min(wordIds.size(), PackageImporter.PACKAGE_SIZE);

            chunk = wordIds.subList(0, end);
            wordIds = wordIds.subList(end, wordIds.size());

            packageCounter++;
            packages.add(this.packageCreator.create(
                type.getId(),
                packageName + "-" + packageCounter,
                chunk
            ));
        }

        return packages;
    }

    private Map<String, Boolean> parseWords(InputStream file) throws IOException, InvalidFileFormatException {
        CSVReader csv = new CSVReader(new InputStreamReader(file));

        Map<String, Boolean> words = new HashMap<>();
        String[] line;
        Integer lineNumber = 0;
        while ((line = csv.readNext()) != null) {
            lineNumber++;
            if (line.length < 1) {
                continue;
            }
            if (line.length > 2) {
                throw new InvalidFileFormatException("More than two columns", lineNumber);
            }

            String word = line[0].trim();
            if (word.length() == 0) {
                throw new InvalidFileFormatException("Empty word column.", lineNumber);
            }

            Boolean belongsToType = null;
            if (line.length > 1) {
                belongsToType = this.parseBelongsToType(line[1], lineNumber);
            }

            words.put(word, belongsToType);
        }

        return words;
    }

    private Boolean parseBelongsToType(String belongsToType, Integer lineNumber) throws InvalidFileFormatException {
        switch (belongsToType) {
            case "TRUE":
                return true;

            case "FALSE":
            case "ERROR":
                return false;

            default:
                throw new InvalidFileFormatException("Wrong belonging specification.", lineNumber);
        }
    }
}
