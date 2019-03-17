package Application.repo;

import java.util.Set;

import Application.repo.custom.EventGuestRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;

public interface EventGuestRepository extends JpaRepository<EventGuestRelation, Long>, EventGuestRepositoryCustom {

}
