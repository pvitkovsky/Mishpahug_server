package application.repositories;

import application.entities.UserEntity;
import application.repositories.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer>, UserRepositoryCustom {
    //@Query("SELECT u from UserEntity u WHERE u.userName = :userName")
    public UserEntity findByUserName(String userName);
}
