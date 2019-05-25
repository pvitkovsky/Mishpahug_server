package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Integer> {
    public UserSession findByTokenAndIsValidTrue(String token);

    public UserSession findByUserNameAndIpAndUserAgentAndIsValidTrue(String userName, String address, String agent);
}
