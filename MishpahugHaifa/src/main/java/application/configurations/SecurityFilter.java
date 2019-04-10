package application.configurations;

import application.entities.UserSession;
import application.repositories.UserSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    public UserSessionRepository userSessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Security Filter");
        String token = request.getHeader("Authorization");
        if (token != null) {
            log.info("Filter -> token is not null {} ", token);
            UserSession userSession = userSessionRepository.findByTokenAndIsValidTrue(token);
            if (userSession!= null){
                log.info("Filter -> token is valid");

                String usernameForController = request.getHeader("username");

                if (usernameForController == null) {
                    usernameForController = "no name";
                }
                Authentication key = new UsernamePasswordAuthenticationToken(
                        usernameForController,
                        null,
                        new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(key);
            }
        }
        log.info("Filter -> exit");
        filterChain.doFilter(request, response);
    }
}
