package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/*"})
public class CheckSessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws
            ServletException {
    }

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest
                = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        if (!action.equals("login") && !action.isEmpty()
                && !action.endsWith(".css")) {
            // must have HttpSession
            HttpSession session = httpServletRequest.getSession();
            if (session.getAttribute("userName") == null) {
                RequestDispatcher rd = request.
                        getRequestDispatcher("/login");
                rd.forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
