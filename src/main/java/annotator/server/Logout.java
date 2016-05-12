package annotator.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by simon on 1.5.16.
 */
@WebServlet(urlPatterns = {"/Logout"})
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().removeAttribute("authenticatedUser");
        request.getSession().invalidate();
        request.setAttribute("message", "You have been successfully logged out");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
