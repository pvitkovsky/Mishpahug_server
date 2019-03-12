package Application.repo;

import Application.entities.PasswordEntity;
import Application.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordRepository extends JpaRepository<Integer, PasswordEntity> {
    @Query("SELECT pwd from PasswordEntity pwd WHERE pwd.userEntityId = :userId")
    public PasswordEntity getByUserId(Integer userId);
}
