package application.repo;

import application.entities.UserEntity;
import application.repo.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Integer>, UserRepositoryCustom {
    //@Query("SELECT u from UserEntity u WHERE u.userName = :userName")
    public UserEntity findByUserName(String userName);
    
    public UserEntity getByNickname(String nickName); //TODO: working query pls;
}
