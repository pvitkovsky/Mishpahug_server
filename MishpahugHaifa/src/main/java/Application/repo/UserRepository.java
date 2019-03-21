package Application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Application.entities.UserEntity;
import Application.repo.custom.UserRepositoryCustom;


public interface UserRepository extends JpaRepository<UserEntity, Integer>, UserRepositoryCustom {
    @Query("SELECT u from UserEntity u WHERE u.nickname = :nickname")
    public UserEntity getByNickName(String nickname);
}
