package application.models.role;

import org.springframework.data.jpa.repository.JpaRepository;

import application.models.user.UserEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    public UserRoleEntity findByAppUser(UserEntity userEntity);
}
