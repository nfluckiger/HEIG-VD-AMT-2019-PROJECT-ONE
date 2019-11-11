package ch.heigvd.amt.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Check if the user is logged in before to access to a page
 */
@WebFilter(filterName = "SecurityServlet", urlPatterns = "/*")
public class SecurityServlet implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);

        if(!uri.startsWith("officialLeague/assets/") && !uri.endsWith("login") && (session == null || session.getAttribute("user") == null)){
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }
}