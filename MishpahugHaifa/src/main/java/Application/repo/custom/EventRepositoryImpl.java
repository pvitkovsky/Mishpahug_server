package application.repo.custom;

import application.entities.EventEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRepositoryImpl implements EventRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EventEntity> searchByFilter(HashMap<String, String> filter) {
        StringBuilder query = new StringBuilder();
        Map<String, String> parameters = new HashMap<>();
        query.append("select e from EventEntity e where 1=1 ");

        if (filter.containsKey("date")){
            query.append(" and e.date = :date");
            parameters.put("date", filter.get("date"));
        }
        if (filter.containsKey("nameOfEvent")){
            query.append(" and e.nameOfEvent LIKE :nameOfEvent");
            parameters.put("nameOfEvent",  "%" + filter.get("nameOfEvent") + "%");
        }
        if (filter.containsKey("status")){
            query.append(" and e.status = :status");
            parameters.put("status", filter.get("status"));
        }

        Query jpaQuery = entityManager.createQuery(query.toString());
        for (Map.Entry<String,String> map : parameters.entrySet()){
            jpaQuery.setParameter(map.getKey(), map.getValue());
        }

        return jpaQuery.getResultList();
    }
}
