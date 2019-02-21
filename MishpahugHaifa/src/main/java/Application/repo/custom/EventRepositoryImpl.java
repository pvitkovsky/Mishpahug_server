package Application.repo.custom;

import Application.entities.EventEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

public class EventRepositoryImpl implements EventRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EventEntity> searchByFilter(HashMap<String, String> filter) {
        return null;
    }
}
