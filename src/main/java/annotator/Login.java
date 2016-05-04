package annotator;

import annotator.model.user.User;
import annotator.model.user.UserNotFoundException;
import annotator.model.user.UserRepository;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by simon on 3.5.16.
 */
@WebServlet(urlPatterns={"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        MongoClient client = new MongoClient();

        UserRepository repository = new UserRepository(client.getDatabase("annotator"));
        try {
            User user = repository.getOneByEmail(username);
            // todo: check password

            HttpSession session = request.getSession();
            session.setAttribute("authenticatedUser", username);
            response.sendRedirect("index.jsp");

        } catch (UserNotFoundException e) {
            request.setAttribute("message", "Invalid username/password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
