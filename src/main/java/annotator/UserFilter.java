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
        HttpSession session = request.getSession();
        String path = request.getServletPath();

        System.err.println("filter " + path);
        if (path.equals("/index.jsp") || path.equals("/Login")) {
            chain.doFilter(request, response);
            return;
        }

        if (session.getAttribute("authenticatedUser") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
