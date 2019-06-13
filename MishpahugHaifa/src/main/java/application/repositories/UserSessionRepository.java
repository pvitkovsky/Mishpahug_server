package application.repositories;

import application.entities.security.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, Integer> {
    public UserSession findByTokenAndIsValidTrue(String token);

    public UserSession findByUserNameAndIpAndUserAgentAndIsValidTrue(String userName, String address, String agent);
}
