package annotator.server;

import annotator.model.pack.InvalidFileFormatException;
import annotator.model.pack.Package;
import annotator.model.pack.PackageCreateConflictException;
import annotator.model.pack.PackageImporter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/auth/Upload"})
@MultipartConfig
public class UploadController extends Controller {

    private PackageImporter packageImporter;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.packageImporter = serviceLocator.getPackageImporter();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String packageName = request.getParameter("packageName");
        String packageType = request.getParameter("packageType");

        List<Package> packages;

        try {
            packages = this.packageImporter.importPackage(packageType, packageName, request.getPart("file").getInputStream());
            this.template.set("message", "File was successfully uploaded as " + packages.size() + " packages(s)");
            this.render("/auth/upload.jsp", request, response);

        } catch (ServletException | IOException e) {
            this.template.set("alertMessage", "Cannot upload file: " + e.getMessage());
            this.render("/auth/upload.jsp", request, response);

        } catch (InvalidFileFormatException e) {
            String line = "";
            if (e.getLine() != null) {
                line = "Line " + e.getLine() + ": ";
            }

            this.template.set("alertMessage", line + e.getMessage());
            this.render("/auth/upload.jsp", request, response);

        } catch (PackageCreateConflictException e) {
            this.template.set("alertMessage", "Error uploading file :(");
            this.render("/auth/upload.jsp", request, response);
        }
    }
}
