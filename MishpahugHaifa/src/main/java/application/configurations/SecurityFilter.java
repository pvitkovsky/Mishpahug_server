package application.configurations;

import application.MishpohugApplication;
import application.entities.UserEntity;
import application.entities.UserSession;
import application.repositories.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Autowired
    public UserSessionRepository userSessionRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        MishpohugApplication.log.debug("Filter");
        if (request.getRequestURI().contains("/user/register") || request.getRequestURI().contains("/user/login") ||
                (request.getRequestURI().contains("/event/") && request.getMethod().contains("GET"))) {
            chain.doFilter(servletRequest, servletResponse);
            MishpohugApplication.log.debug("Filter -> For guests");
            return;
        }
        String token = request.getHeader("Authorization");
        if (token == null) {
            MishpohugApplication.log.debug("Filter -> token is null");
            response.sendError(401);
            return;
        }
        UserSession userSession = userSessionRepository.findByTokenAndIsValidTrue(token);
        if (userSession == null) {
            MishpohugApplication.log.debug("Filter -> token is not correctly");
            response.sendError(401);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
