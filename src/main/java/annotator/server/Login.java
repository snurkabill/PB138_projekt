package annotator.server;

import annotator.model.user.User;
import annotator.model.user.UserNotFoundException;
import annotator.model.user.UserRepository;
import com.mongodb.client.MongoDatabase;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns={"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserRepository userRepository = new UserRepository(
            (MongoDatabase) this.getServletContext().getAttribute("database")
        );

        try {
            User user = userRepository.getOneByEmail(username);
            if (!BCrypt.checkpw(password, user.getPasswordHash())) {
                throw new InvalidPasswordException();
            }

            HttpSession session = request.getSession();
            session.setAttribute("authenticatedUser", username);
            response.sendRedirect("index.jsp");

        } catch (UserNotFoundException | InvalidPasswordException e) {
            request.setAttribute("message", "Invalid username/password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
