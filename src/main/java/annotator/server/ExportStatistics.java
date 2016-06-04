package annotator.server;


import annotator.model.statistics.StatisticsToXmlExporter;
import annotator.model.statistics.collector.AllStatisticsCollector;
import annotator.model.statistics.collector.UserStatisticsCollector;
import annotator.model.statistics.collector.WordStatisticsCollector;
import annotator.model.user.User;
import annotator.model.vote.VoteRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = {"/auth/Export"})
public class ExportStatistics extends Controller {

    private AllStatisticsCollector allStatisticsCollector;
    private UserStatisticsCollector userStatisticsCollector;
    private WordStatisticsCollector wordStatisticsCollector;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        VoteRepository voteRepository = serviceLocator.getVoteRepository();
        this.allStatisticsCollector = new AllStatisticsCollector(voteRepository);
        this.userStatisticsCollector = new UserStatisticsCollector(voteRepository);
        this.wordStatisticsCollector = new WordStatisticsCollector(voteRepository);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = getServletContext().getRealPath("") + "auth/output.xml";
        PrintWriter printWriter = new PrintWriter(filePath);
        String output = "";

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


