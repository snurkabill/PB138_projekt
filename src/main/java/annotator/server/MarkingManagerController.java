package annotator.server;

import annotator.model.activepackage.ActivePackage;
import annotator.model.activepackage.ActivePackageNotFoundException;
import annotator.model.activepackage.ActivePackageRepository;
import annotator.model.pack.Package;
import annotator.model.pack.PackageNotFoundException;
import annotator.model.pack.PackageRepository;
import annotator.model.type.TypeNotFoundException;
import annotator.model.type.TypeRepository;
import annotator.model.user.User;
import annotator.model.vote.VoteRepository;
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
    private ActivePackageRepository activePackageRepository;
    private WordRepository wordRepository;
    private TypeRepository typeRepository;
    private VoteRepository voteRepository;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.packageRepository = serviceLocator.getPackageRepository();
        this.activePackageRepository = serviceLocator.getActivePackageRepository();
        this.wordRepository = serviceLocator.getWordRepository();
        this.typeRepository = serviceLocator.getTypeRepository();
        this.voteRepository = serviceLocator.getVoteRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("packageId") != null) {

            if (!this.loadWord(request.getParameter("packageId"))) {
                response.sendRedirect("package-list");

            } else {
                this.render(
                        "answerBlock.jsp",
                        request,
                        response
                );
            }

        } else if (request.getParameter("vote") != null) {
            this.vote(
                request.getParameter("package"),
                request.getParameter("vote"),
                Long.parseLong(request.getParameter("start")),
                request.getParameter("word")
            );
            response.sendRedirect("marking-manager?packageId=".concat(request.getParameter("package")));

        } else {
            System.out.println("invalid page");
            this.template.set("message", "invalid page");
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
            ActivePackage activePackage = this.activePackageRepository.getOrMakeNew(
                packageId,
                ((User) this.session.getAttribute("loggedUser")).getId(),
                this.packageRepository
            );

            Package pack = this.packageRepository.getPackage(activePackage.getPackageId());
            if (activePackage.getProgress().equals(pack.getWordCount())) {
                return false;
            }

            ArrayList<String> words = pack.getWordList();
            Word word = this.wordRepository.getWord(words.get(activePackage.getProgress()));

            this.template.set("packageId", activePackage.getId());
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

    private void vote(String activePackageId, String vote, long longDuration, String wordId) {
        try {
            Date now = new Date();
            Integer duration = ((Long) (now.getTime() - longDuration)).intValue();

            ActivePackage activePackage = this.activePackageRepository.getActivePackage(activePackageId);

            this.voteRepository.addVote(
                ((User) session.getAttribute("loggedUser")).getId(),
                wordRepository.getWord(wordId),
                vote.equals("yes"),
                duration
            );
            this.activePackageRepository.update(activePackage.increaseProgress());

        } catch (ActivePackageNotFoundException | WordNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            this.template.set("message", "invalid page");
        }
    }
}
