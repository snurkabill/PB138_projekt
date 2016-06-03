package annotator.server;


import annotator.model.activepackage.ActivePackageCreator;
import annotator.model.pack.PackageRepository;
import annotator.model.statistics.StatisticsToXmlExporter;
import annotator.model.statistics.collector.AllStatisticsCollector;
import annotator.model.statistics.collector.UserStatisticsCollector;
import annotator.model.statistics.collector.WordStatisticsCollector;
import annotator.model.statistics.domain.AllStatistics;
import annotator.model.statistics.domain.Statistics;
import annotator.model.statistics.domain.UserStatistics;
import annotator.model.statistics.domain.WordStatistics;
import annotator.model.type.TypeRepository;
import annotator.model.user.User;
import annotator.model.user.UserRepository;
import annotator.model.vote.VoteRepository;
import annotator.model.word.WordRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = {"/auth/Export"})
public class ExportStatistics extends Controller {

    private VoteRepository voteRepository;
    private WordRepository wordRepository;
    private AllStatisticsCollector allStatisticsCollector;
    private UserStatisticsCollector userStatisticsCollector;
    private WordStatisticsCollector wordStatisticsCollector;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.voteRepository = serviceLocator.getVoteRepository();
        this.wordRepository = serviceLocator.getWordRepository();
        this.allStatisticsCollector = new AllStatisticsCollector(this.voteRepository, this.wordRepository);
        this.userStatisticsCollector = new UserStatisticsCollector(this.voteRepository);
        this.wordStatisticsCollector = new WordStatisticsCollector(this.voteRepository);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = getServletContext().getRealPath("") + "auth/output.xml";
        PrintWriter printWriter = new PrintWriter(filePath);
        String output = "";

        System.out.println(this.voteRepository.getAllVotes().toString());
        switch (request.getParameter("mode")) {
            case "all" :
                output = new StatisticsToXmlExporter(
                        allStatisticsCollector.getAllStatistics()
                ).getInXml();
                break;
            case "user" :
                output = new StatisticsToXmlExporter(
                        userStatisticsCollector
                                .getUserStatistics(((User)this.session.getAttribute("loggedUser")).getId())
                ).getInXml();
                break;
            case "allUser" :
                output = new StatisticsToXmlExporter(
                        userStatisticsCollector.getAllUserStatistics()
                ).getInXml();
                break;
            case "word" :
                output = new StatisticsToXmlExporter(
                        wordStatisticsCollector.getAllWordStatistics()
                ).getInXml();
                break;
        }

        printWriter.append(output);
        printWriter.close();
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);

        ServletContext context = getServletContext();

        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}


