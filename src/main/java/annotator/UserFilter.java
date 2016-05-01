package annotator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * This should prevent unauthorized access to application
 * Created by simon on 1.5.16.
 */
@WebFilter(urlPatterns = {"/*"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String auth = request.getHeader("Authorization");
        if (auth == null) {
            response401(response);
            return;
        }

        String[] creds = parseAuthHeader(auth);
        String username = creds[0];
        String password = creds[1];

        /* here goes check with users in mongoDB */
        if (username.equals("admin") && password.equals("admin")) {
            HttpSession session = request.getSession();
            session.setAttribute("authenticatedUser", username);
            chain.doFilter(request, response);
        } else {
            response401(response);
            return;
        }
    }

    private String[] parseAuthHeader(String auth) {
        return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"Annotator\"");
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1></body></html>");
    }

    @Override
    public void destroy() {

    }
}
