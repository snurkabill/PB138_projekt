package annotator.server;

import annotator.model.user.User;
import annotator.model.user.UserCreateConflictException;
import annotator.model.user.UserNotFoundException;
import annotator.model.user.UserRepository;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoDatabase;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/CreateUser"})
public class CreateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt());

        UserRepository userRepository = new UserRepository(
            (MongoDatabase) this.getServletContext().getAttribute("database")
        );

        try {
            User user = userRepository.createUser(username, password);
            if (user.getId() == null) {
                throw new UserCreateConflictException("User already Exists");
            }

            HttpSession session = request.getSession();
            session.setAttribute("authentUser", user);
            session.setAttribute("authenticatedUser", username);
            response.sendRedirect("index.jsp");

        } catch (UserCreateConflictException | MongoWriteException | UserNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("message", "Username already exists");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
