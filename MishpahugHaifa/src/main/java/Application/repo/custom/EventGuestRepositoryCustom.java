package Application.repo.custom;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;

import java.util.Set;

public interface EventGuestRepositoryCustom {
    public Set<EventGuestRelation> findAllByUser(UserEntity user);
    public Set<EventGuestRelation> findAllByEvent(EventEntity Event);
}
