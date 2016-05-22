package annotator.server;

import annotator.model.user.User;
import annotator.model.user.UserCreateConflictException;
import annotator.model.user.UserCreator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/CreateUser"})
public class CreateUser extends Controller {

    private UserCreator userCreator;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.userCreator = serviceLocator.getUserCreator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = this.userCreator.create(username, password);

            this.session.setAttribute("authentUser", user);
            this.session.setAttribute("authenticatedUser", username);
            response.sendRedirect("index.jsp");

        } catch (UserCreateConflictException e) {
            this.template.set("message", "Username already exists");
            this.render(
                "index.jsp",
                request,
                response
            );
        }
    }
}
