package application.configurations;

import application.repositories.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter {

    @Autowired
    public UserSessionRepository userSessionRepository;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;

        System.out.println("Filter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
