package Application.repo;

import Application.entities.FeedBackEntity;
import Application.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<FeedBackEntity, Integer> {
    /*проверить не уверен*/
    @Query("SELECT fb from FeedBackEntity fb WHERE fb.userItem like %:userEntity%")
    public List<FeedBackEntity> getByUser(UserEntity userEntity);
    @Query("DELETE from FeedBackEntity fb WHERE fb.userItem like %:userEntity%")
    public List<FeedBackEntity> removeByUser(UserEntity userEntity);
}
