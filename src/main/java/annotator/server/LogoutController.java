package annotator.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/auth/Logout"})
public class LogoutController extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.session.removeAttribute("authenticatedUser");
        this.session.invalidate();
        this.template.set("message", "You have been successfully logged out");

        response.sendRedirect("/Login");
    }
}
