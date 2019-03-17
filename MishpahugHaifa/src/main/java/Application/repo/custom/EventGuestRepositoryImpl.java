package Application.repo.custom;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;

import java.util.Set;

public class EventGuestRepositoryImpl implements EventGuestRepositoryCustom {
    @Override
    public Set<EventGuestRelation> findAllByUser(UserEntity user) {
        return null;
    }

    @Override
    public Set<EventGuestRelation> findAllByEvent(EventEntity Event) {
        return null;
    }
}
