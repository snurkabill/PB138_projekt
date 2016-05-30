package annotator.server;

import annotator.model.activepackage.ActivePackage;
import annotator.model.activepackage.ActivePackageNotFoundException;
import annotator.model.activepackage.ActivePackageRepository;
import annotator.model.vote.Voter;
import annotator.model.word.WordNotFoundException;
import annotator.model.word.WordRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = {"/auth/vote"})
public class VoteController extends Controller {

    private Voter voter;
    private ActivePackageRepository activePackageRepository;
    private WordRepository wordRepository;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.voter = serviceLocator.getVoter();
        this.activePackageRepository = serviceLocator.getActivePackageRepository();
        this.wordRepository = serviceLocator.getWordRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String packageId = request.getParameter("packageId");
        String vote = request.getParameter("vote");
        Long start = Long.parseLong(request.getParameter("start"));
        String word = request.getParameter("word");

        this.vote(
            packageId,
            vote,
            start,
            word
        );
        response.sendRedirect("marking-manager?packageId=" + request.getParameter("packageId"));
    }

    private void vote(String packageId, String vote, long longDuration, String wordId) {
        try {
            Date now = new Date();
            Integer duration = ((Long) (now.getTime() - longDuration)).intValue();

            ActivePackage activePackage = this.activePackageRepository.getActivePackage(
                packageId,
                this.getUser().getId()
            );

            this.voter.vote(
                this.getUser().getId(),
                this.wordRepository.getWord(wordId),
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
