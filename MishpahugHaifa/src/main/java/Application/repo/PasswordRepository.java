package Application.repo;

import Application.entities.PasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordRepository extends JpaRepository<PasswordEntity, Integer> {
    @Query("SELECT pwd from PasswordEntity pwd WHERE pwd.userEntityId = :userId")
    public PasswordEntity getByUserId(Integer userId);
}
