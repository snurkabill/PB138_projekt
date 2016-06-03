package annotator.server;

import annotator.model.activepackage.ActivePackage;
import annotator.model.activepackage.ActivePackageCreator;
import annotator.model.pack.Package;
import annotator.model.pack.PackageNotFoundException;
import annotator.model.pack.PackageRepository;
import annotator.model.type.TypeNotFoundException;
import annotator.model.type.TypeRepository;
import annotator.model.word.Word;
import annotator.model.word.WordNotFoundException;
import annotator.model.word.WordRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(urlPatterns = {"/auth/marking-manager"})
public class MarkingManagerController extends Controller {

    private PackageRepository packageRepository;
    private WordRepository wordRepository;
    private TypeRepository typeRepository;
    private ActivePackageCreator activePackageCreator;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.packageRepository = serviceLocator.getPackageRepository();
        this.wordRepository = serviceLocator.getWordRepository();
        this.typeRepository = serviceLocator.getTypeRepository();
        this.activePackageCreator = serviceLocator.getActivePackageCreator();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!this.loadWord(request.getParameter("packageId"))) {
            response.sendRedirect("package-list");

        } else {
            this.render(
                "answerBlock.jsp",
                request,
                response
            );
        }
    }

    private boolean loadWord(String packageId) {
        try {
            Date date = new Date();
            this.template.set("start", date.getTime());

            ActivePackage activePackage = this.activePackageCreator.createIfDoesNotExist(packageId, this.getUser().getId());

            Package pack = this.packageRepository.getPackage(activePackage.getPackageId());
            if (activePackage.getProgress().equals(pack.getWordCount())) {
                return false;
            }

            ArrayList<String> words = pack.getWordList();
            Word word = this.wordRepository.getWord(words.get(activePackage.getProgress()));

            this.template.set("packageId", pack.getId());
            this.template.set("word", word);
            this.template.set("currentPosition", activePackage.getProgress() + 1);
            this.template.set("fullSize", pack.getWordCount());
            this.template.set("type", this.typeRepository.getType(word.getTypeId()));

        } catch (PackageNotFoundException | TypeNotFoundException | WordNotFoundException e) {
            e.printStackTrace();
            this.template.set("message", "Package not found");
        }

        return true;
    }
}
