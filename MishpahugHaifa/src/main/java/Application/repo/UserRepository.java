package Application.repo;

import Application.entities.UserEntity;
import Application.repo.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer>, UserRepositoryCustom {
}
