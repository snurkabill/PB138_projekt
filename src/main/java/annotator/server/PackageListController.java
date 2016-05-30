package annotator.server;

import annotator.model.pack.Package;
import annotator.model.pack.PackageRepository;
import annotator.model.type.Type;
import annotator.model.type.TypeRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/auth/package-list"})
public class PackageListController extends Controller {

    private PackageRepository packageRepository;
    private TypeRepository typeRepository;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.packageRepository = serviceLocator.getPackageRepository();
        this.typeRepository = serviceLocator.getTypeRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> types = this.typeRepository.getTypesList();

        HashMap<String, List<Package>> packages = new HashMap<>();
        for (Type type: types) {
            packages.put(
                type.getId(),
                this.packageRepository.getPackagesByType(type)
            );
        }

        this.template.set("types", types);
        this.template.set("typePackages", packages);

        this.render(
            "markWords.jsp",
            request,
            response
        );
    }
}
