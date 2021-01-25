package by.prus.finalproject.service.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class SessionLocaleFilter implements Filter {
    private static final String LOCALIZATION = "localization";
    private static final String INIT_PARAMETER = "ru";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        String currentLocalization = (String) session.getAttribute(LOCALIZATION);
        if (currentLocalization == null) {
            session.setAttribute(LOCALIZATION, INIT_PARAMETER);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() { }
}
