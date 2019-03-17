package Application.repo;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;
import Application.repo.custom.UserRepositoryCustom;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserEntity, Integer>, UserRepositoryCustom {
    @Query("SELECT u from UserEntity u WHERE u.nickname = :nickname")
    public UserEntity getByNickName(String nickname);

}
