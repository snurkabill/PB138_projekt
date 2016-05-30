package annotator.server;

import annotator.model.user.User;
import annotator.model.user.UserNotFoundException;
import annotator.model.user.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/Login"})
public class LoginController extends Controller {

    private UserRepository userRepository;

    @Override
    protected void initializeDependencies(ServiceLocator serviceLocator) {
        this.userRepository = serviceLocator.getUserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = this.userRepository.getOneByEmail(username);
            if (!BCrypt.checkpw(password, user.getPasswordHash())) {
                throw new InvalidPasswordException();
            }

            this.session.setAttribute("authenticatedUser", username);
            this.session.setAttribute("loggedUser", user);
            this.session.setAttribute("database", this.getServletContext().getAttribute("database"));
            response.sendRedirect("auth/index.jsp");

        } catch (UserNotFoundException | InvalidPasswordException | IllegalArgumentException e) {
            this.template.set("message", "Invalid username/password");
            this.render("/WEB-INF/login.jsp", request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.render(
            "/WEB-INF/login.jsp",
            request,
            response
        );
    }
}
