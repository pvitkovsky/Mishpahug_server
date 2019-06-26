package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.models.user.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Integer> {
    public UserSession findByTokenAndIsValidTrue(String token);
    
    public UserSession findFirstByTokenAndIsValidTrue(String token);

    public UserSession findByUserNameAndIpAndUserAgentAndIsValidTrue(String userName, String address, String agent);
    
    public UserSession findFirstByUserNameAndIpAndUserAgentAndIsValidTrue(String userName, String address, String agent);
}
