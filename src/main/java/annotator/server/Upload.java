package annotator.server;

import annotator.model.pack.PackageCreateConflictException;
import annotator.model.pack.PackageCreator;
import annotator.model.type.*;
import annotator.model.word.Word;
import annotator.model.word.WordCreateConflictException;
import annotator.model.word.WordCreator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@WebServlet(urlPatterns = {"/auth/Upload"})
@MultipartConfig
public class Upload extends Controller {

    private PackageCreator packageCreator;
    private WordCreator wordCreator;
    private TypeCreator typeCreator;
    private TypeRepository typeRepository;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.packageCreator = serviceLocator.getPackageCreator();
        this.wordCreator = serviceLocator.getWordCreator();
        this.typeCreator = serviceLocator.getTypeCreator();
        this.typeRepository = serviceLocator.getTypeRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* TODO: check file format */

        try {
            String packageType = request.getParameter("packageType");
            Type type = typeRepository.findOneByType(packageType);
            Part file = request.getPart("file");
            Scanner fileContent = new Scanner(file.getInputStream(), "UTF-8").useDelimiter("\\A");
            ArrayList<String> words = new ArrayList<>();

            if (type == null) {
                type = typeCreator.create(packageType);
            }

            String typeId = type.getId();
            int wordCounter = 0;
            int packageCounter = 0;

            while (fileContent.hasNextLine()) {
                if (wordCounter == 1000) {
                    packageCreator.create(typeId, words.size(), words);
                    ++packageCounter;
                    words.clear();
                }

                String line = fileContent.nextLine();
                String[] parts = line.split(",");
                String wordName = parts[0];
                Word word;

                if (parts[1] == null) {
                    word = wordCreator.create(typeId, wordName, null);
                    System.err.println("null1");
                } else if (parts[1].equals("TRUE")) {
                    System.err.println("true");
                    word = wordCreator.create(typeId, wordName, Boolean.TRUE);
                } else if (parts[1].equals("FALSE") || parts[1].equals("ERROR")) {
                    word = wordCreator.create(typeId, wordName, Boolean.FALSE);
                    System.err.println("false");
                } else {
                    word = wordCreator.create(typeId, wordName, null);
                }

                words.add(word.getId());
                ++wordCounter;
            }

            packageCreator.create(typeId, words.size(), words);
            ++packageCounter;

            this.template.set("message", "File was successfully uploaded as " + packageCounter + (packageCounter == 1 ? " package" : "packages"));
            this.render("/auth/upload.jsp", request, response);

        } catch (IOException | WordCreateConflictException | PackageCreateConflictException | TypeCreateConflictException e) {
            this.template.set("alertMessage", "Cannot upload file: " + e.getMessage());
            this.render("/auth/upload.jsp", request, response);
        }

    }



}


