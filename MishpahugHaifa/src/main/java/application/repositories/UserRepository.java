package application.repositories;

import application.entities.GenderEntity;
import application.entities.UserEntity;
import application.repositories.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Integer>, UserRepositoryCustom {
    //@Query("SELECT u from UserEntity u WHERE u.userName = :userName")
    public List<UserEntity> findByGenderEntity(GenderEntity genderEntity);
    public UserEntity findByUserName(String userName);
}
