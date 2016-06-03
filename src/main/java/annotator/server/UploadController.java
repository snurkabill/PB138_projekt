package annotator.server;

import annotator.model.pack.PackageCreateConflictException;
import annotator.model.pack.PackageCreator;
import annotator.model.type.*;
import annotator.model.word.Word;
import annotator.model.word.WordCreateConflictException;
import annotator.model.word.WordCreator;
import annotator.model.word.WordRepository;
import au.com.bytecode.opencsv.CSVReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/auth/Upload"})
@MultipartConfig
public class UploadController extends Controller {

    private PackageCreator packageCreator;
    private WordCreator wordCreator;
    private WordRepository wordRepository;
    private TypeCreator typeCreator;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.packageCreator = serviceLocator.getPackageCreator();
        this.wordCreator = serviceLocator.getWordCreator();
        this.wordRepository = serviceLocator.getWordRepository();
        this.typeCreator = serviceLocator.getTypeCreator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String packageName = request.getParameter("packageName");
        String packageType = request.getParameter("packageType");
        CSVReader csv;

        try {
            Part file = request.getPart("file");
            csv = new CSVReader(new InputStreamReader(file.getInputStream()));

        } catch (ServletException | IOException e) {
            this.template.set("alertMessage", "Cannot upload file: " + e.getMessage());
            this.render("/auth/upload.jsp", request, response);
            return;
        }

        Type type = this.typeCreator.createIfDoesNotExist(packageType);

        ArrayList<String> wordIds = null;
        ArrayList<Word> words = new ArrayList<>();
        String typeId = type.getId();
        int wordCounter = 0;
        int packageCounter = 0;

        try {
            String[] line;
            while ((line = csv.readNext()) != null) {

                if (wordCounter % 1000 == 0 && wordCounter != 0) {
                    /* Create new package */
                    wordIds = this.insertWordsToDatabase(words);

                    this.packageCreator.create(typeId, packageName + "-" + packageCounter, wordIds);
                    ++packageCounter;
                    words.clear();
                    wordIds.clear();
                }

                String wordName = line[0];
                if (wordName.equals("")) {
                    throw new InvalidFileFormatException("Invalid file format on line " + (wordCounter + 1));
                }

                Boolean belongsToType;
                if (line.length == 1) {
                    belongsToType = null;

                } else if (line.length == 2) {
                    switch (line[1]) {
                        case "TRUE":
                            belongsToType = Boolean.TRUE;
                            break;
                        case "FALSE":
                        case "ERROR":
                            belongsToType = Boolean.FALSE;
                            break;
                        default:
                            throw new InvalidFileFormatException("Invalid file format on line " + (wordCounter + 1));
                    }

                } else {
                    throw new InvalidFileFormatException("Invalid file format on line " + (wordCounter + 1));
                }

                words.add(new Word(null, typeId, wordName, belongsToType));
                ++wordCounter;
            }

            if (wordCounter == 0) {
                throw new InvalidFileFormatException("File is empty");
            }

            wordIds = this.insertWordsToDatabase(words);
            this.packageCreator.create(typeId, (packageCounter > 1) ? packageName + "-" + packageCounter : packageName, wordIds);
            ++packageCounter;

            this.template.set("message", "File was successfully uploaded as " + packageCounter + (packageCounter == 1 ? " package" : " packages"));
            this.render("/auth/upload.jsp", request, response);

        } catch (InvalidFileFormatException | WordCreateConflictException | PackageCreateConflictException e) {
            if (wordIds != null) {
                for (String wordId : wordIds) {
                    this.wordRepository.removeWord(wordId);
                }
            }
            this.template.set("alertMessage", "Error uploading file: " + e.getMessage() + "<br/>" + packageCounter + (packageCounter == 1 ? " package" : " packages") + " created");
            this.render("/auth/upload.jsp", request, response);
        }
    }

    private ArrayList<String> insertWordsToDatabase(ArrayList<Word> words) throws WordCreateConflictException {

        ArrayList<String> wordIds = new ArrayList<>();

        try {
            for (Word w : words) {
                Word insertedWord = this.wordCreator.create(w.getTypeId(), w.getWord(), w.belongsToType());
                wordIds.add(insertedWord.getId());
            }

        } catch (WordCreateConflictException e) {
            for (String wordId : wordIds) {
                this.wordRepository.removeWord(wordId);
            }
            throw new WordCreateConflictException(e.getMessage());
        }

        return wordIds;
    }
}
