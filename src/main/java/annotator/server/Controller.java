package annotator.server;

import annotator.model.user.User;
import annotator.server.template.TemplateParameters;
import com.mongodb.client.MongoDatabase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public abstract class Controller extends HttpServlet {

    private ServiceLocator services;
    protected TemplateParameters template;
    protected MongoDatabase database;
    protected HttpSession session;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.template = new TemplateParameters();
        this.database = (MongoDatabase) request.getServletContext().getAttribute("database");
        this.services = new ServiceLocator(this.database);
        this.session = request.getSession();
        this.initializeDependencies(this.services);
        super.service(request, response);
    }

    protected void initializeDependencies(ServiceLocator serviceLocator) {

    }

    protected User getUser() {
        return (User) this.session.getAttribute("loggedUser");
    }

    protected void render(String templateName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (Map.Entry<String, Object> entry: this.template.getParameters()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        request
            .getRequestDispatcher(templateName)
            .forward(request, response);
    }
}
