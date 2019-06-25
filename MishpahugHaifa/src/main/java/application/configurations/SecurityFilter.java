package application.configurations;

import application.models.user.UserSession;
import application.repositories.UserSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${random-uuid}")
	private boolean randomUUID;

    @Autowired
    public UserSessionRepository userSessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Security Filter");
        log.info("Security Filter -> Remote address -> " + request.getRemoteAddr());
        log.info("Security Filter -> Remote port -> " + request.getRemotePort());
        String token = request.getHeader("Authorization");
        if (token != null) {
            log.info("Security Filter -> token -> {}", token);          
            UserSession userSession;
            if(randomUUID) {
            	userSession = userSessionRepository.findByTokenAndIsValidTrue(token);
            } else {
            	userSession = userSessionRepository.findFirstByTokenAndIsValidTrue(token);
            }
            if (userSession != null) {
                log.info("Security Filter -> token is valid");

                String usernameForController = userSession.getUserName();

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
        log.info("Security Filter -> exit");
        filterChain.doFilter(request, response);
    }
}
