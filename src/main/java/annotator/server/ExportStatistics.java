package annotator.server;


import annotator.model.activepackage.ActivePackageCreator;
import annotator.model.pack.PackageRepository;
import annotator.model.type.TypeRepository;
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

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.voteRepository = serviceLocator.getVoteRepository();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = getServletContext().getRealPath("") + "auth/output.xml";
        PrintWriter printWriter = new PrintWriter(filePath);
        
        //        File outputFile;
//        if (this.session.getAttribute("authenticatedUser") == "admin") {
//            //admin can export all data
//            //File my_file =
//
//        }else {
//            //user can export only his data
//            //File my_file =
//        }



        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);
        // if you want to use a relative path to context root:
        String relativePath = getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);

        // obtains ServletContext
        ServletContext context = getServletContext();

        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }


//
//        // You must tell the browser the file type you are going to send
//        // for example application/pdf, text/plain, text/html, image/jpg
//        response.setContentType("text/xml");
//
//        // Make sure to show the download dialog
//        response.setHeader("Content-disposition", "attachment; filename=output.xml");
//
//        // Assume file name is retrieved from database
//        // For example D:\\file\\test.pdf
//
//
//        // This should send the file to browser
//        OutputStream out = response.getOutputStream();
//        FileInputStream in = new FileInputStream("auth/output.xml");
//        byte[] buffer = new byte[4096];
//        int length;
//        while ((length = in.read(buffer)) > 0) {
//            out.write(buffer, 0, length);
//        }
//        in.close();
//        out.flush();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}


