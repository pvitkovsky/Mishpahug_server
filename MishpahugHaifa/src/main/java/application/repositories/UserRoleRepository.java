package application.repositories;

import application.entities.UserEntity;
import application.entities.security.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    public UserRoleEntity findByAppUser(UserEntity userEntity);
}
