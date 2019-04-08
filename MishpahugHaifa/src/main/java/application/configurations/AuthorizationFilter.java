package application.configurations;

import application.entities.UserSession;
import application.repositories.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter {

    @Autowired
    public UserSessionRepository userSessionRepository;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURI().equals("/register") || request.getRequestURI().equals("/login")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        String token = request.getHeader("Authorization");
        if (token == null) {
            response.sendError(401);
            return;
        }
        UserSession userSession = userSessionRepository.findByTokenAAndIsValidTrue(token);
        if (userSession == null) {
            response.sendError(401);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
