package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.UserEntity;
import application.entities.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    public UserRoleEntity findByAppUser(UserEntity userEntity);
}
